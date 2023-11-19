package com.github.trcdeveloppers.clayium.client.model

import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ItemOverrideList
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.vertex.VertexFormat
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.IModel
import net.minecraftforge.common.model.IModelState

class ClayContainerPipeModel(
    private val machineHullTier: Int,
) : IModel {

    override fun getTextures(): MutableCollection<ResourceLocation> {
        return mutableListOf(
            ResourceLocation("clayium:blocks/machinehull-$machineHullTier"),
        )
    }

    override fun bake(
        state: IModelState,
        format: VertexFormat,
        bakedTextureGetter: java.util.function.Function<ResourceLocation, TextureAtlasSprite>
    ): IBakedModel {
        return ClayContainerPipeBakedModel(bakedTextureGetter, machineHullTier)
    }

    private class ClayContainerPipeBakedModel(
        bakedTextureGetter: java.util.function.Function<ResourceLocation, TextureAtlasSprite>,
        tier: Int,
    ) : IBakedModel {

        private val machineHull = bakedTextureGetter.apply(ResourceLocation("clayium:blocks/machinehull-$tier"))

        override fun isAmbientOcclusion() = true
        override fun isGui3d() = true
        override fun isBuiltInRenderer() = false

        override fun getParticleTexture(): TextureAtlasSprite = this.machineHull
        override fun getOverrides(): ItemOverrideList = ItemOverrideList.NONE

        override fun getQuads(state: IBlockState?, side: EnumFacing?, rand: Long): MutableList<BakedQuad> {
            TODO("Not yet implemented")
        }

    }
}