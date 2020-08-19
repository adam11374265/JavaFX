package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

public class Main extends Application
{

    double W = 600, H = 400;
    double x = 300, y = 200;

    Rectangle rectangle = new Rectangle(150, 75, 150, 150);


    @Override
    public void start(Stage stage) throws Exception
    {

        Group root = new Group(rectangle);

        rectangle.relocate(x, y);

        Scene scene = new Scene(root, W, H);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                switch (event.getCode())
                {
                    case UP:    y -=1; rectangle.relocate(x, y); break;
                    case DOWN:  y +=1; rectangle.relocate(x, y); break;
                    case LEFT:  x -= 1; rectangle.relocate(x ,y); break;
                    case RIGHT: x +=1; rectangle.relocate(x, y); break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                switch (event.getCode())
                {
                    case UP:    y -=1; rectangle.relocate(x, y); break;
                    case DOWN:  y +=1; rectangle.relocate(x, y); break;
                    case LEFT:  x -= 1; rectangle.relocate(x ,y); break;
                    case RIGHT: x +=1; rectangle.relocate(x, y); break;
                }
            }
        });

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
