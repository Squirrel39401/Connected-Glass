package com.cypermethrin.connectedglass.BlockBakedModel;

import com.cypermethrin.connectedglass.BlockBakedModel.Glass.GlassModel;
import com.cypermethrin.connectedglass.BlockBakedModel.StainedGlasses.*;
import com.cypermethrin.connectedglass.ConnectedGlass;
import net.fabricmc.fabric.api.renderer.v1.sprite.FabricSpriteAtlasTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.block.model.TextureSlots;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ArrayListDeque;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Util {
	public static final Identifier LOCATION_BLOCKS = TextureAtlas.LOCATION_BLOCKS;

    public static void init(){
        registerCustomModel(Blocks.GLASS, new GlassModel());
        registerCustomModel(Blocks.WHITE_STAINED_GLASS, new WhiteGlassModel());
        registerCustomModel(Blocks.PINK_STAINED_GLASS, new PinkGlassModel());
        registerCustomModel(Blocks.TINTED_GLASS, new TintedGlass());
        registerCustomModel(Blocks.BLACK_STAINED_GLASS, new BlackGlassModel());
        registerCustomModel(Blocks.BLUE_STAINED_GLASS, new BlueGlassModel());
        registerCustomModel(Blocks.CYAN_STAINED_GLASS, new CyanGlassModel());
        registerCustomModel(Blocks.BROWN_STAINED_GLASS, new BrownGlassModel());
        registerCustomModel(Blocks.GRAY_STAINED_GLASS, new GrayGlassModel());
        registerCustomModel(Blocks.GREEN_STAINED_GLASS, new GreenGlassModel());
        registerCustomModel(Blocks.LIGHT_BLUE_STAINED_GLASS, new LightBlueGlassModel());
        registerCustomModel(Blocks.LIGHT_GRAY_STAINED_GLASS, new LightGrayGlassModel());
        registerCustomModel(Blocks.LIME_STAINED_GLASS, new LimeGlassModel());
        registerCustomModel(Blocks.MAGENTA_STAINED_GLASS, new MagentaGlassModel());
        registerCustomModel(Blocks.ORANGE_STAINED_GLASS, new OrangeGlassModel());
        registerCustomModel(Blocks.PURPLE_STAINED_GLASS, new PurpleGlassModel());
        registerCustomModel(Blocks.RED_STAINED_GLASS, new RedGlassModel());
        registerCustomModel(Blocks.YELLOW_STAINED_GLASS, new YellowGlassModel());


    }
    public static void registerCustomModel(Block block, BlockStateModel model){
        MAP.put(block, model);
    }
	public static HashMap<Block, BlockStateModel> MAP = new HashMap<>();


    public static boolean[] isGlass(Direction direction, BlockAndTintGetter world, BlockPos pos){
        //up down left right
        boolean[] booleans = new boolean[8];
        if(direction == Direction.DOWN){
            booleans[0] = isGlass(world, pos.south());
            booleans[1] = isGlass(world, pos.north());
            booleans[2] = isGlass(world, pos.west());
            booleans[3] = isGlass(world, pos.east());


            booleans[4] = isGlass(world, pos.south().west());
            booleans[5] = isGlass(world, pos.south().east());
            booleans[6] = isGlass(world, pos.north().west());
            booleans[7] = isGlass(world, pos.north().east());
        }
        else if(direction.getAxis() == Direction.Axis.Y){
            booleans[0] = isGlass(world, pos.north());
            booleans[1] = isGlass(world, pos.south());
            booleans[2] = isGlass(world, pos.west());
            booleans[3] = isGlass(world, pos.east());


            booleans[4] = isGlass(world, pos.north().west());
            booleans[5] = isGlass(world, pos.north().east());
            booleans[6] = isGlass(world, pos.south().west());
            booleans[7] = isGlass(world, pos.south().east());
        }
        else if(direction.getAxis() == Direction.Axis.X){
            booleans[0] = isGlass(world, pos.above());
            booleans[1] = isGlass(world, pos.below());
            booleans[2] = direction == Direction.EAST ? isGlass(world, pos.south()) : isGlass(world, pos.north());
            booleans[3] = direction == Direction.EAST ? isGlass(world, pos.north()) : isGlass(world, pos.south());

            booleans[4] = isGlass(world, direction == Direction.EAST ? pos.above().south() : pos.above().north());
            booleans[5] = isGlass(world, direction == Direction.EAST ? pos.above().north() : pos.above().south());
            booleans[6] = isGlass(world, direction == Direction.EAST ? pos.below().south() : pos.below().north());
            booleans[7] = isGlass(world, direction == Direction.EAST ? pos.below().north() : pos.below().south());
        }
        else if(direction.getAxis() == Direction.Axis.Z){
            booleans[0] = isGlass(world, pos.above());
            booleans[1] = isGlass(world, pos.below());
            booleans[2] = direction == Direction.SOUTH ? isGlass(world, pos.west()) : isGlass(world, pos.east());
            booleans[3] = direction == Direction.SOUTH ? isGlass(world, pos.east()) : isGlass(world, pos.west());

            booleans[4] = isGlass(world, direction == Direction.SOUTH ? pos.above().west() : pos.above().east());
            booleans[5] = isGlass(world, direction == Direction.SOUTH ? pos.above().east() : pos.above().west());
            booleans[6] = isGlass(world, direction == Direction.SOUTH ? pos.below().west() : pos.below().east());
            booleans[7] = isGlass(world, direction == Direction.SOUTH ? pos.below().east() : pos.below().west());
        }
        return booleans;
    }

    public static List<String> getDirectionSets(boolean up, boolean down, boolean left, boolean right){
        ArrayList<String> list = new ArrayList<>();
        NonNullList<String> list1 = NonNullList.create();
        if(up)list.add("up");
        if(down)list.add("down");
        if(left)list.add("left");
        if(right)list.add("right");
        for(ArrayList<String> arrayList : getSets2(list)){
            list1.add(arrayList.getFirst() + "_" + arrayList.getLast());
        }
        return list1;
    }

    private static <T> List<ArrayList<T>> getSets2(ArrayList<T> array){
        return getSubsets(array).stream().filter(ts -> ts.size() == 2).toList();
    }

    private static <T> ArrayList<ArrayList<T>> getSubsets(ArrayList<T> array){
        ArrayList<ArrayList<T>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        for(T t : (ArrayList<T>)array.clone()){
            for(ArrayList<T> arrayList : (ArrayList<ArrayList<T>>)list.clone()){
                ArrayList<T> cloned = (ArrayList<T>) arrayList.clone();
                cloned.add(t);
                list.add(cloned);
            }
        }
        return list;
    }

    public static final HashMap<String, Integer> map = new HashMap<String, Integer>(){{
        put("", 0);
        put("up", 1);
        put("down", 2);
        put("left", 3);
        put("right", 4);
        put("up_down", 5);
        put("up_left", 6);
        put("up_right", 7);
        put("down_left", 8);
        put("down_right", 9);
        put("left_right", 10);
        put("up_down_left", 11);
        put("up_down_right", 12);
        put("up_left_right", 13);
        put("down_left_right", 14);
        put("up_down_left_right", 15);
        put("pixel_up_left", 16);
        put("pixel_up_right", 17);
        put("pixel_down_left", 18);
        put("pixel_down_right", 19);
        put("pixel_up_left_up_right", 20);
        put("pixel_up_left_down_left", 21);
        put("pixel_up_left_down_right", 22);
        put("pixel_up_right_down_left", 23);
        put("pixel_up_right_down_right", 24);
        put("pixel_down_left_down_right", 25);
        put("pixel_up_left_up_right_down_left", 26);
        put("pixel_up_left_up_right_down_right", 27);
        put("pixel_up_left_down_left_down_right", 28);
        put("pixel_up_right_down_left_down_right", 29);
        put("pixel_up_left_up_right_down_left_down_right", 30);
        put("pixel", 31);
    }};


    public static boolean isGlass(BlockAndTintGetter view, BlockPos pos){
        return view.getBlockState(pos).is(BlockTags.IMPERMEABLE);
    }

    public static Material[] getSpriteIdentifiers(String namespace){
        return new Material[]{
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/glass")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/glass_up")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/glass_down")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/glass_left")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/glass_right")), // 5 index 4
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/glass_up_down")),//6 index 5
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/glass_up_left")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/glass_up_right")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/glass_down_left")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/glass_down_right")), // 10
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/glass_left_right")),// 11 index 10
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/glass_up_down_left")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/glass_up_down_right")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/glass_up_left_right")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/glass_down_left_right")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/glass_up_down_left_right")),//16 index 15 block ends
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/pixel_up_left")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/pixel_up_right")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/pixel_down_left")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/pixel_down_right")),//i 19
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/pixel_up_left_up_right")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/pixel_up_left_down_left")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/pixel_up_left_down_right")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/pixel_up_right_down_left")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/pixel_up_right_down_right")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/pixel_down_left_down_right")),// i 25
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/pixel_up_left_up_right_down_left")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/pixel_up_left_up_right_down_right")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/pixel_up_left_down_left_down_right")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/pixel_up_right_down_left_down_right")),
                new Material(LOCATION_BLOCKS, of("block/"+namespace+"/pixel_up_left_up_right_down_left_down_right")),
                new Material(LOCATION_BLOCKS, of("block/" + namespace + "/pixel"))
        };
    }
    public static Identifier of(String path){
        return Identifier.fromNamespaceAndPath(ConnectedGlass.MOD_ID, path);
    }


    public static TextureSlots.Data getTextures(Factory factory){
        TextureSlots.Data.Builder builder = new TextureSlots.Data.Builder();
        for(Map.Entry<String, Integer> entry : Util.map.entrySet()){
            builder.addTexture(entry.getKey(), factory.getSpriteIdentifier(entry.getValue()));
        }
        return builder.build();
    }

    public interface Factory{
        Material getSpriteIdentifier(int index);
    }

	public static TextureAtlasSprite getSprite(Material material){
		return Minecraft.getInstance().getAtlasManager().get(material);
	}


    public static TextureAtlasSprite getSprite(boolean up, boolean down, boolean left, boolean right, Factory factory){
        String string = "";
        if(up)string += "_up";
        if(down)string += "_down";
        if(left)string += "_left";
        if(right)string += "_right";
        if(string.length() > 1)string = string.substring(1);
        Integer i = map.get(string);
        return getSprite(factory.getSpriteIdentifier(i));
    }

    public static TextureAtlasSprite getSprite(byte b, Factory factory){
        int i = getOrCreateCache(b)[0];
        return getSprite(factory.getSpriteIdentifier(i));
    }

    @Nullable
    public static TextureAtlasSprite getPixelSprite(byte b, Factory factory){
        int[] i = getOrCreateCache(b);
        if(i[0] == 0)return null;
        return getSprite(factory.getSpriteIdentifier(i[1]));
    }

    public static TextureAtlasSprite getPixelSprite(boolean up, boolean down, boolean left, boolean right, boolean up_left, boolean up_right, boolean down_left, boolean down_right, Factory factory){
        String string = "pixel";
        if(up || left || up_left)string += "_up_left";
        if(up || right || up_right)string += "_up_right";
        if(down || left || down_left)string += "_down_left";
        if(down || right || down_right)string += "_down_right";
        return getSprite(factory.getSpriteIdentifier(map.get(string)));
    }
    /*
    1代表有 0代表无
    *5  1  6
    *3     4
    *7  2  8
    * */
    private static int[] getOrCreateCache(byte booleans){
        if(byte2intarray.containsKey(booleans))return byte2intarray.get(booleans);
        else {
            int[] ints = new int[2];
            StringBuilder border = new StringBuilder();
            StringBuilder pixel = new StringBuilder("pixel");
            String s;
            boolean[] glasses = new boolean[8];
            for(int i = 0;i < 8; i++){
                glasses[i] = ((booleans >> i & 1) == 1);
            }

            if(glasses[0])border.append("_up");
            if(glasses[1])border.append("_down");
            if(glasses[2])border.append("_left");
            if(glasses[3])border.append("_right");
            if(!border.isEmpty())s = border.substring(1);
            else s = "";
            ints[0] = map.get(s);

            if(!glasses[0] || !glasses[2] || !glasses[4])pixel.append("_up_left");
            if(!glasses[0] || !glasses[3] || !glasses[5])pixel.append("_up_right");
            if(!glasses[1] || !glasses[2] || !glasses[6])pixel.append("_down_left");
            if(!glasses[1] || !glasses[3] || !glasses[7])pixel.append("_down_right");
            ints[1] = map.get(pixel.toString());

            byte2intarray.put(booleans, ints);
            return ints;
        }
    }

    public static byte getOrCreateCache(boolean[] booleans){
        if(booleans.length > 8)return 0;
        StringBuilder builder = new StringBuilder();
        for(boolean bool : booleans){
            builder.append(bool);
        }
        String b = builder.toString();
        if(booleans2byte.containsKey(b))return booleans2byte.get(b);
        else {
            byte b1 = 0;
            for(int i = 0;i < booleans.length; i++){
                b1 += (byte) (getIntByBool(booleans[i]) << i);
            }
            booleans2byte.put(b, b1);
            return b1;
        }
    }

    private static int getIntByBool(boolean b){
        return b ? 1 : 0;
    }

    private static final HashMap<Byte, int[]> byte2intarray = new HashMap<>();
    private static final HashMap<String, Byte> booleans2byte = new HashMap<>();
}
