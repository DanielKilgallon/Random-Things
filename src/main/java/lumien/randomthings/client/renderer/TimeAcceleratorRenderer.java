package lumien.randomthings.client.renderer;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class TimeAcceleratorRenderer extends EntityRenderer {

    public TimeAcceleratorRenderer(EntityRendererManager erm) {
        super(erm);
    }


//    @Override
    public EntityRenderer createRenderFor(EntityRendererManager manager) {
        return null;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return null;
    }
}