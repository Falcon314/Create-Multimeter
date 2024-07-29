package com.Falcon.cmultimeter.registry;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.kinetics.gauge.GaugeGenerator;
import com.simibubi.create.content.kinetics.motor.CreativeMotorGenerator;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.content.redstone.displayLink.source.KineticSpeedDisplaySource;
import com.simibubi.create.content.redstone.displayLink.source.KineticStressDisplaySource;
import com.simibubi.create.foundation.block.ItemUseOverrides;
import com.simibubi.create.foundation.data.*;
import com.simibubi.create.foundation.utility.Couple;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.util.ForgeSoundType;
import com.Falcon.cmultimeter.MultimeterCreate;
import com.Falcon.cmultimeter.content.blocks.kinetics.multimeter.MultiMeterBlock;

import java.util.function.Consumer;

import static com.simibubi.create.AllMovementBehaviours.movementBehaviour;
import static com.simibubi.create.content.redstone.displayLink.AllDisplayBehaviours.assignDataBehaviour;
import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.*;
import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static com.Falcon.cmultimeter.MultimeterCreate.REGISTRATE;

@SuppressWarnings({"unused", "removal", "all"})
public class MultimeterBlocks {

	public static final BlockEntry<MultiMeterBlock> MULTIMETER = REGISTRATE.block("multimeter", MultiMeterBlock::new)
			.initialProperties(SharedProperties::wooden)
			.properties(p -> p.mapColor(MapColor.PODZOL))
			.transform(axeOrPickaxe())
			.transform(BlockStressDefaults.setNoImpact())
			.blockstate(new GaugeGenerator()::generate)
			.onRegister(assignDataBehaviour(new KineticSpeedDisplaySource(), "kinetic_speed"))
			.onRegister(assignDataBehaviour(new KineticStressDisplaySource(), "kinetic_stress"))
			.recipe((c, p) -> ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, c.get(), 2)
					.requires(AllBlocks.STRESSOMETER.get())
					.requires(AllBlocks.SPEEDOMETER.get())
					.unlockedBy("has_compass", has(Items.COMPASS))
					.save(p, MultimeterCreate.asResource("crafting/multimeter")))
			.item()
			.tab(MultimeterCreativeModeTabs.BASE_CREATIVE_TAB.getKey())
			.transform(ModelGen.customItemModel("gauge", "_", "item"))
			.register();

	public static void register() {}

}
