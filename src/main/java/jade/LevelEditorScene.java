package jade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import components.Sprite;
import components.SpriteRenderer;
import components.Spritesheet;
import imgui.ImGui;
import org.joml.Vector2f;
import org.joml.Vector4f;
import renderer.Texture;
import util.AssetPool;


public class LevelEditorScene extends Scene {

    private GameObject obj1;
    private Spritesheet sprites;

    public LevelEditorScene() {
    }

    @Override
    public void init() {
        loadResources();
        this.camera = new Camera(new Vector2f(-250, 0));
        if (levelLoaded) {
            return;
        }

        sprites = AssetPool.getSpritesheet("assets/images/spritesheet.png");

        obj1 = new GameObject("Object 1", new Transform(new Vector2f(200, 100), new Vector2f(256, 256)), 2);
        SpriteRenderer obj1SpriteRenderer = new SpriteRenderer();
        obj1SpriteRenderer.setColor(new Vector4f(1, 0, 0, 1));
        obj1.addComponent(obj1SpriteRenderer);
        this.addGameObjectToScene(obj1);

        GameObject obj2 = new GameObject("Object 2", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)), 3);
        SpriteRenderer obj2SpriteRenderer = new SpriteRenderer();
        Sprite obj2Sprite = new Sprite();
        obj2Sprite.setTexture(AssetPool.getTexture("assets/images/blendImage2.png"));
        obj2SpriteRenderer.setSprite(obj2Sprite);
        obj2.addComponent(obj2SpriteRenderer);
        this.addGameObjectToScene(obj2);
        this.activeGameObject = obj2;
    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");
        AssetPool.addSpritesheet("assets/images/spritesheet.png",
                new Spritesheet(AssetPool.getTexture("assets/images/spritesheet.png"),
                        16, 16, 26, 0));
    }

    @Override
    public void update(float dt) {

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }

    @Override
    public void imgui() {
        ImGui.begin("Test window");
        ImGui.text("xxxx");
        ImGui.end();
    }
}