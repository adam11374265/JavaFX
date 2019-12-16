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
  private static final double W = 600, H = 400;
  boolean goNorth,goSouth, goEast, goWest;
  
   public void start(Stage stage) throws Exception
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

        final double cx = rectangle.getBoundsInLocal().getWidth()  / 2;
        final double cy = rectangle.getBoundsInLocal().getHeight() / 2;

        double x = cx + rectangle.getLayoutX() + dx;
        double y = cy + rectangle.getLayoutY() + dy;

        moveRectagleto(x, y);
    }

    private void moveRectagleto(double x, double y) {
        final double cx = rectangle.getBoundsInLocal().getWidth()  / 2;
        final double cy = rectangle.getBoundsInLocal().getHeight() / 2;

        if (x - cx >= 0 &&
            x + cx <= W &&
            y - cy >= 0 &&
            y + cy <= H) {
            rectangle.relocate(x - cx, y - cy);
        }
    }

    public static void main(String[] args) 
    { 
      launch(args); 
    }
}
