package com.Falcon.cmultimeter.registry;

import com.simibubi.create.content.kinetics.base.*;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.Falcon.cmultimeter.content.blocks.kinetics.multimeter.MultiMeterBlockEntity;

import static com.Falcon.cmultimeter.MultimeterCreate.REGISTRATE;

public class MultimeterBlockEntityTypes {

	public static final BlockEntityEntry<MultiMeterBlockEntity> MULTIMETER = REGISTRATE
			.blockEntity("multimeter", MultiMeterBlockEntity::new)
			.instance(() -> ShaftInstance::new)
			.validBlocks(MultimeterBlocks.MULTIMETER)
			.renderer(() -> ShaftRenderer::new)
			.register();

	public static void register() {}
}
