package lumien.randomthings.client;

import lumien.randomthings.client.renderer.TimeAcceleratorRenderer;
import lumien.randomthings.tileentity.TimeAcceleratorEntity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy {
    public ClientProxy() {
        RenderingRegistry.registerEntityRenderingHandler(TimeAcceleratorEntity.class, TimeAcceleratorRenderer::new);
    }
}
