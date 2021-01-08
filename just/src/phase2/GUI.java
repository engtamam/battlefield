package phase2;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GUI extends Application {
	double x=0,y=0;
	public GUI() {
	}

	Battlefield battle = new Battlefield();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		// fighter 1
		Circle fighter1 = new Circle(10);
		fighter1.setFill(Color.GREEN);
		fighter1.setCenterX(battle.fighter.get(0).getPosition().getX() * 10);
		fighter1.setCenterY(battle.fighter.get(0).getPosition().getY() * 10);
		String label1 = String.format("fighter 1, weapon:%s",
				battle.fighter.get(0).weapon[battle.fighter.get(0).weaponIndix].getWeaponName());
		Label l1 = new Label(label1);
		l1.setAlignment(Pos.TOP_RIGHT);

		Rectangle shell1_Stroke = new Rectangle(100, 10);
		shell1_Stroke.setFill(Color.WHITE);
		shell1_Stroke.setStroke(Color.BLUE);

		Rectangle shell1 = new Rectangle(battle.fighter.get(0).shell[battle.fighter.get(0).shellIndix].getHp(), 10);
		shell1.setFill(Color.BLUE);

		StackPane spShell1 = new StackPane();
		spShell1.setAlignment(Pos.TOP_RIGHT);
		spShell1.getChildren().addAll(shell1_Stroke, shell1);
		spShell1.setAlignment(Pos.TOP_RIGHT);
		Rectangle hp1Stroke = new Rectangle(100, 20);
		hp1Stroke.setFill(Color.WHITE);
		hp1Stroke.setStroke(Color.GREEN);

		Rectangle hp1 = new Rectangle(battle.fighter.get(0).getHp(), 20);

		hp1.setFill(Color.GREEN);

		StackPane spHp1 = new StackPane();
		spHp1.setAlignment(Pos.TOP_RIGHT);
		spHp1.getChildren().addAll(hp1Stroke, hp1);

		// fighter 2
		Circle fighter2 = new Circle(10);
		fighter2.setFill(Color.RED);
		fighter2.setCenterX(battle.fighter.get(1).getPosition().getX() * 10);
		fighter2.setCenterY(battle.fighter.get(1).getPosition().getY() * 10);

		String label2 = String.format("fighter 2, weapon:%s",
				battle.fighter.get(1).weapon[battle.fighter.get(1).weaponIndix].getWeaponName());
		Label l2 = new Label(label2);

		// shell
		Rectangle shell2_Stroke = new Rectangle(100, 10);
		shell2_Stroke.setFill(Color.WHITE);
		shell2_Stroke.setStroke(Color.YELLOW);

		Rectangle shell2 = new Rectangle(battle.fighter.get(1).shell[battle.fighter.get(1).shellIndix].getHp(), 10);
		shell2.setFill(Color.YELLOW);

		StackPane spShell2 = new StackPane();
		spShell2.setAlignment(Pos.TOP_LEFT);
		spShell2.getChildren().addAll(shell2_Stroke, shell2);
		// hp
		Rectangle hp2Stroke = new Rectangle(100, 20);
		hp2Stroke.setFill(Color.WHITE);
		hp2Stroke.setStroke(Color.RED);

		Rectangle hp2 = new Rectangle(battle.fighter.get(0).getHp(), 20);
		hp2.setFill(Color.RED);

		StackPane spHp2 = new StackPane();
		spHp2.setAlignment(Pos.TOP_LEFT);
		spHp2.getChildren().addAll(hp2Stroke, hp2);

		// output
		Button b1 = new Button("strt a round");
		Button b2 = new Button("new battle");

		b1.setAlignment(Pos.BOTTOM_CENTER);
		Label l = new Label();
//		Circle c = new Circle(3);
//		c.setFill(Color.BLACK);

		////////////// panes//////////////////////
		Pane p = new Pane();
		p.setPrefWidth(50);
		p.setPrefHeight(50);
		p.getChildren().addAll(fighter1, fighter2);
		p.setPadding(new Insets(30));

		GridPane gp = new GridPane();
		gp.addColumn(0, l1, spHp1, spShell1);
		gp.addColumn(1, l2, spHp2, spShell2);
		gp.setHgap(200);
		gp.setVgap(2);

		HBox output = new HBox(50);
		output.getChildren().addAll(b1, b2, l);
		output.setPadding(new Insets(30));

		BorderPane bp = new BorderPane();
		bp.setCenter(p);
		bp.setTop(gp);
		bp.setPadding(new Insets(30));
		bp.setBottom(output);




		///////////////// events//////////////////////////
		b1.setOnAction(e->{
				int lineColor;
				if (battle.fighter.get(0).getHp() > 0 && battle.fighter.get(1).getHp() > 0) {
					lineColor = battle.fight();

					shell1.setWidth(battle.fighter.get(0).shell[battle.fighter.get(0).shellIndix].getHp());
					hp1.setWidth(battle.fighter.get(0).getHp());
					fighter1.setCenterX(battle.fighter.get(0).getPosition().getX() * 10);
					fighter1.setCenterY(battle.fighter.get(0).getPosition().getY() * 10);

					shell2.setWidth(battle.fighter.get(1).shell[battle.fighter.get(1).shellIndix].getHp());
					hp2.setWidth(battle.fighter.get(1).getHp());
					fighter2.setCenterX(battle.fighter.get(1).getPosition().getX() * 10);
					fighter2.setCenterY(battle.fighter.get(1).getPosition().getY() * 10);

					if (battle.fighter.get(0).shell[battle.fighter.get(0).shellIndix].getHp() > 0) {
						fighter1.setFill(Color.BLUE);
						if (lineColor == 0) {
							x = fighter1.getCenterX();
							y = fighter1.getCenterY();

							Circle c = new Circle(3);
							c.setFill(Color.BLACK);
							p.getChildren().add(c);

							Line path = new Line(x, y, fighter2.getCenterX(), fighter2.getCenterY());
							path.setStroke(Color.color(0, 0, 0, 0));
							p.getChildren().add(path);

							PathTransition pt = new PathTransition(Duration.seconds(1), path, c);
							pt.setAutoReverse(false);
							pt.setCycleCount(1);
							pt.play();
							FadeTransition ft = new FadeTransition(Duration.seconds(1), c);
							ft.setAutoReverse(false);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.setByValue(0.2);
							ft.play();
						}
					} else {
						fighter1.setFill(Color.GREEN);
						if (lineColor == 0) {
							x = fighter1.getCenterX();
							y = fighter1.getCenterY();

							Circle c = new Circle(3);
							c.setFill(Color.BLACK);
							p.getChildren().add(c);

							Line path = new Line(x, y, fighter2.getCenterX(), fighter2.getCenterY());
							path.setStroke(Color.color(0, 0, 0, 0));
							p.getChildren().add(path);

							PathTransition pt = new PathTransition(Duration.seconds(1), path, c);
							pt.setAutoReverse(false);
							pt.setCycleCount(1);
							pt.play();
							FadeTransition ft = new FadeTransition(Duration.seconds(1), c);
							ft.setAutoReverse(false);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.setByValue(0.2);
							ft.play();
						}
					}
					if (battle.fighter.get(1).shell[battle.fighter.get(1).shellIndix].getHp() > 0) {
						fighter2.setFill(Color.YELLOW);

						if (lineColor == 1) {
							x = fighter2.getCenterX();
							y = fighter2.getCenterY();

							Circle c = new Circle(3);
							c.setFill(Color.BLACK);
							p.getChildren().add(c);

							Line path = new Line(x, y, fighter1.getCenterX(), fighter1.getCenterY());
							path.setStroke(Color.color(0, 0, 0, 0));
							p.getChildren().add(path);
							PathTransition pt = new PathTransition(Duration.seconds(1), path, c);
							pt.setAutoReverse(false);
							pt.setCycleCount(1);
							pt.play();
							FadeTransition ft = new FadeTransition(Duration.seconds(1), c);
							ft.setAutoReverse(false);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.setByValue(0.2);
							ft.play();
							 }
						} else {
							fighter2.setFill(Color.RED);

							if (lineColor == 1) {
								x = fighter2.getCenterX();
								y = fighter2.getCenterY();

								Circle c = new Circle(3);
								c.setFill(Color.BLACK);
								p.getChildren().add(c);

								Line path = new Line(x, y, fighter1.getCenterX(), fighter1.getCenterY());
								path.setStroke(Color.color(0, 0, 0, 0));
								p.getChildren().add(path);
								PathTransition pt = new PathTransition(Duration.seconds(1), path, c);
								pt.setAutoReverse(false);
								pt.setCycleCount(1);
								pt.play();
								FadeTransition ft = new FadeTransition(Duration.seconds(1), c);
								ft.setAutoReverse(false);
								ft.setFromValue(1);
								ft.setToValue(0);
								ft.setByValue(0.2);
								ft.play();
							}

						}
					}else {
						if (battle.fighter.get(0).getHp() < 1) {
							l.setText("fighter 1 has won the fight");
							fighter1.setFill(Color.BLACK);
						} else {
							l.setText("FIGHTER 2 HAS WON THE FIGHT");
							fighter2.setFill(Color.BLACK);
							}
					}

				}


		);
		b2.setOnAction(e->{
		battle = new Battlefield();
		shell1.setWidth(battle.fighter.get(0).shell[battle.fighter.get(0).shellIndix].getHp());// shell
																								// =100
		// shell_2
		shell2.setWidth(battle.fighter.get(1).shell[battle.fighter.get(1).shellIndix].getHp());// shell
																								// =100;
		hp1.setWidth(battle.fighter.get(0).getHp()); // hp1 =100
		hp2.setWidth(battle.fighter.get(1).getHp());// hp2 =100

		fighter2.setCenterX(battle.fighter.get(1).getPosition().getX() * 10);
		fighter2.setCenterY(battle.fighter.get(1).getPosition().getY() * 10);
		fighter2.setFill(Color.YELLOW);

		fighter1.setCenterX(battle.fighter.get(0).getPosition().getX() * 10);
		fighter1.setCenterY(battle.fighter.get(0).getPosition().getY() * 10);
		fighter1.setFill(Color.BLUE);

		l.setText("");

	});

		stage.setScene(new Scene(bp, 600, 500));
		stage.setTitle("Battle Field");
		stage.getIcons().add((new Image("phase2/icon.jpg")));
		stage.setTitle("BattleField");
		stage.show();

	}

}
