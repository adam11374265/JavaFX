Package sample;
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.shape.Rectangle;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;



public class Main extends Application 
{ 
   @Override 
   public void start(Stage stage) 
   { 
      Rectangle rectangle = new Rectangle(150, 170, 150, 150);     
      Group root = new Group(rectangle); 
      Scene scene = new Scene(root, 600, 300);
	    scene.setOnKeyPressed(new EventHandler<KeyEvent>() 
        {
          @Override
            public void handle(KeyEvent event) {
              switch (event.getCode()) {
                case UP:    goNorth = true; break;
                case DOWN:  goSouth = true; break;
                case LEFT:  goWest  = true; break;
                case RIGHT: goEast  = true; break;
                case SHIFT: running = true; break;
             }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    goNorth = false; break;
                    case DOWN:  goSouth = false; break;
                    case LEFT:  goWest  = false; break;
                    case RIGHT: goEast  = false; break;
                    case SHIFT: running = false; break;
                }
            }
        });

        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;

                if (goNorth) dy -= 1;
                if (goSouth) dy += 1;
                if (goEast)  dx += 1;
                if (goWest)  dx -= 1;
                

                moveRectangleBy(dx, dy);
            }
        };
        timer.start();
    }

    private void moveRectangleBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = scene.getBoundsInLocal().getWidth()  / 2;
        final double cy = scene.getBoundsInLocal().getHeight() / 2;

        double x = cx + scene.getLayoutX() + dx;
        double y = cy + scene.getLayoutY() + dy;

        moveRectagleto(x, y);
    }

    private void moveRectagleto(double x, double y) {
        final double cx = scene.getBoundsInLocal().getWidth()  / 2;
        final double cy = scene.getBoundsInLocal().getHeight() / 2;

        if (x - cx >= 0 &&
            x + cx <= W &&
            y - cy >= 0 &&
            y + cy <= H) {
            scene.relocate(x - cx, y - cy);
        }
    }

    public static void main(String[] args) 
    { 
      launch(args); 
    }
}
