package com.github.trcdeveloppers.clayium.client.loader

import com.github.trcdeveloppers.clayium.common.Clayium
import com.github.trcdeveloppers.clayium.client.model.ClayBufferModel
import com.github.trcdeveloppers.clayium.client.model.ClayBufferPipeModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.resources.IResourceManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ICustomModelLoader
import net.minecraftforge.client.model.IModel
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
object ClayBufferModelLoader : ICustomModelLoader {

    private val alphabetAndUnderline = Regex("[A-z_]+")

    override fun onResourceManagerReload(resourceManager: IResourceManager) {}

    override fun accepts(modelLocation: ResourceLocation): Boolean {
        if (!(modelLocation.namespace == Clayium.MOD_ID
                && modelLocation is ModelResourceLocation
                && "is_pipe" in modelLocation.variant)) {
            return false
        }
        val registryName = modelLocation.path
        return registryName.startsWith("clay_buffer")
    }

    override fun loadModel(modelLocation: ResourceLocation): IModel {
        val modelResourceLocation = modelLocation as ModelResourceLocation
        val registryName = modelLocation.path
        val isPipe = modelResourceLocation.getVariantValue("is_pipe")?.toBoolean() ?: false
        Clayium.LOGGER.info("Loading model for $modelLocation, tier: ${registryName.replace(alphabetAndUnderline, "").toInt()}")
        val tier = registryName.replace(alphabetAndUnderline, "").toInt()
        return if (isPipe) ClayBufferPipeModel(tier) else ClayBufferModel(tier)
    }
}