package com.github.trcdeveloppers.clayium.common.interfaces

import net.minecraft.block.properties.IProperty
import net.minecraft.block.properties.PropertyBool

interface IBlockPipable {
    var isPipe: Boolean

    companion object {
        val isPipe: IProperty<Boolean> = PropertyBool.create("is_pipe")
    }
}
