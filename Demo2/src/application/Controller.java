package application;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Oxyz.plane_line_vector.Point;
import Oxyz.plane_line_vector.Vector3D;
import Oxyz.room.Room;
import Oxyz.shape.Rectangular;
import Oxyz.camera.Camera;
import properties.CameraProperties;
import properties.ObjectProperties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Controller implements Initializable {

	private Room room = new Room(0, 0, 0);
	private boolean check = false; // bien kiem tra da tao room hay chua, neu chua se khong the chuyen pane

	/********************************
	 * [ Switch Pane Event ]
	 ***********************************/

	@FXML
	AnchorPane background;
	@FXML
	AnchorPane createRoomPane;
	@FXML
	AnchorPane addObjectPane;
	@FXML
	AnchorPane addCameraPane;
	@FXML
	AnchorPane viewPane;
	@FXML
	AnchorPane checkPointPane;
	@FXML
	AnchorPane detailPane;

	@FXML
	Button createRoomButton;
	@FXML
	Button addObjectButton;
	@FXML
	Button addCameraButton;
	@FXML
	Button viewButton;
	@FXML
	Button checkPointButton;
	@FXML
	Button DetailButton;

	@FXML
	ImageView image1;
	@FXML
	ImageView image2;
	@FXML
	ImageView image3;
	@FXML
	ImageView image4;

	// Switch Pane Event

	public void goToHomePage(ActionEvent e) {
		background.setVisible(true);
	}

	public void removeBackground(ActionEvent e) {
		background.setVisible(false);
	}

	public void accessCreateRoomPane(ActionEvent e) {
		createRoomPane.setVisible(true);
		addObjectPane.setVisible(false);
		addCameraPane.setVisible(false);
		viewPane.setVisible(false);
		checkPointPane.setVisible(false);
		detailPane.setVisible(false);
	}

	public void accessAddObjectPane(ActionEvent e) {
		if (check) {
			createRoomPane.setVisible(false);
			addObjectPane.setVisible(true);
			addCameraPane.setVisible(false);
			viewPane.setVisible(false);
			checkPointPane.setVisible(false);
			detailPane.setVisible(false);
		} else {
			Dialog<String> dialog = new Dialog<>();
			dialog.setTitle("NOTIFICATION");
			dialog.setContentText("Please create new room first!");
			dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
			dialog.show();
		}
	}

	public void accessAddCameraPane(ActionEvent e) {
		if (check) {
			createRoomPane.setVisible(false);
			addObjectPane.setVisible(false);
			addCameraPane.setVisible(true);
			viewPane.setVisible(false);
			checkPointPane.setVisible(false);
			detailPane.setVisible(false);
		} else {
			Dialog<String> dialog = new Dialog<>();
			dialog.setTitle("NOTIFICATION");
			dialog.setContentText("Please create new room first!");
			dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
			dialog.show();
		}
	}

	public void accessViewPane(ActionEvent e) {
		if (check) {
			createRoomPane.setVisible(false);
			addObjectPane.setVisible(false);
			addCameraPane.setVisible(false);
			viewPane.setVisible(true);
			checkPointPane.setVisible(false);
			detailPane.setVisible(false);

//			flatViewDraw();
		} else {
			Dialog<String> dialog = new Dialog<>();
			dialog.setTitle("NOTIFICATION");
			dialog.setContentText("Please create new room first!");
			dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
			dialog.show();
		}
	}

	public void accessCheckPointPane(ActionEvent e) {
		if (check) {
			createRoomPane.setVisible(false);
			addObjectPane.setVisible(false);
			addCameraPane.setVisible(false);
			viewPane.setVisible(false);
			checkPointPane.setVisible(true);
			detailPane.setVisible(false);
		} else {
			Dialog<String> dialog = new Dialog<>();
			dialog.setTitle("NOTIFICATION");
			dialog.setContentText("Please create new room first!");
			dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
			dialog.show();
		}
	}

	public void accessDetailPane(ActionEvent e) {
		if (check) {
			createRoomPane.setVisible(false);
			addObjectPane.setVisible(false);
			addCameraPane.setVisible(false);
			viewPane.setVisible(false);
			checkPointPane.setVisible(false);
			detailPane.setVisible(true);
		} else {
			Dialog<String> dialog = new Dialog<>();
			dialog.setTitle("NOTIFICATION");
			dialog.setContentText("Please create new room first!");
			dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
			dialog.show();
		}
	}

//	public void setImageVisible(Boolean b) {
//		image1.setVisible(b);
//		image2.setVisible(b);
//		image3.setVisible(b);
//		image4.setVisible(b);
//	}

	/********************************
	 * [ Button Event ]
	 ***********************************/

	/***** [ Create room event ] *****/

	@FXML
	TextField widthTextFieldCreateRoom, lengthTextFieldCreateRoom, heightTextFieldCreateRoom; // TextField nhap gia tri
																								// chieu rong, dai, cao
																								// cua can phong
	@FXML
	Label widthLabelCreateRoom, lengthLabelCreateRoom, heightLabelCreateRoom; // Cac canh bao input loi tuong ung
	@FXML
	Button addButtonCreateRoom;

	/**
	 * event handle create-room-task
	 */

	public void createRoomEvent(ActionEvent e) {
		double width, length, height;

		width = getAndParse(widthTextFieldCreateRoom, widthLabelCreateRoom);
		if (width != -1) {
			length = getAndParse(lengthTextFieldCreateRoom, lengthLabelCreateRoom);
			if (length != -1) {
				height = getAndParse(heightTextFieldCreateRoom, heightLabelCreateRoom);
				if (height != -1) {
					room = new Room(width, length, height);

					Dialog<String> dialog = new Dialog<>();
					dialog.setTitle("NOTIFICATION");
					dialog.setContentText("Create room success!");
					dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
					dialog.show();

					widthTextFieldCreateRoom.setEditable(false);
					lengthTextFieldCreateRoom.setEditable(false);
					heightTextFieldCreateRoom.setEditable(false);

					check = true;

					// System.out.println(width + "-" + length + "-" +height);

					// widthLabelCreateRoom.setVisible(false);
					// lengthLabelCreateRoom.setVisible(false);
					// heightLabelCreateRoom.setVisible(false);
				}
			}
		}
	}

	/***** [ Restart Program ] *****/

	public void restartEvent(ActionEvent e) {
		room = null;
		check = false;

		widthTextFieldCreateRoom.setEditable(true);
		lengthTextFieldCreateRoom.setEditable(true);
		heightTextFieldCreateRoom.setEditable(true);

		widthTextFieldCreateRoom.setText(null);
		lengthTextFieldCreateRoom.setText(null);
		heightTextFieldCreateRoom.setText(null);
	}

	/***** [ Add Object Event ] *****/

	@FXML
	TextField XA1TextFieldAddObject, YA1TextFieldAddObject, ZA1TextFieldAddObject; // TextField nhap toa do x, y, z cua
																					// dinh A1 cua vat the
	@FXML
	Label XA1LabelAddObject, YA1LabelAddObject, ZA1LabelAddObject; // Cac canh bao loi lien quan

	@FXML
	TextField XB1TextFieldAddObject, YB1TextFieldAddObject, ZB1TextFieldAddObject;
	@FXML
	Label XB1LabelAddObject, YB1LabelAddObject, ZB1LabelAddObject;

	@FXML
	TextField XC1TextFieldAddObject, YC1TextFieldAddObject, ZC1TextFieldAddObject;
	@FXML
	Label XC1LabelAddObject, YC1LabelAddObject, ZC1LabelAddObject;

	@FXML
	TextField heightTextFieldAddObject; // TextField nhap chieu cao vat the
	@FXML
	Label heightLabelAddObject;

	public void addObjectEvent(ActionEvent e) {

		Point A1 = new Point(), B1 = new Point(), C1 = new Point();

		// Lay du lieu cho A1
		double xA1 = 0, yA1 = 0, zA1 = 0;

		xA1 = getAndParse(XA1TextFieldAddObject, XA1LabelAddObject);
		if (xA1 != -1) {
			yA1 = getAndParse(YA1TextFieldAddObject, YA1LabelAddObject);
			if (yA1 != -1) {
				zA1 = getAndParse(ZA1TextFieldAddObject, ZA1LabelAddObject);
				if (zA1 != -1) {
					A1 = new Point(xA1, yA1, zA1);
				} else {
					return;
				}
			} else {
				return;
			}
		} else {
			return;
		}

		// Lay du lieu cho B1
		double xB1 = 0, yB1 = 0, zB1 = 0;
		xB1 = getAndParse(XB1TextFieldAddObject, XB1LabelAddObject);
		if (xB1 != -1) {
			yB1 = getAndParse(YB1TextFieldAddObject, YB1LabelAddObject);
			if (yB1 != -1) {
				zB1 = getAndParse(ZB1TextFieldAddObject, ZB1LabelAddObject);
				if (zB1 != -1) {
					B1 = new Point(xB1, yB1, zB1);
				} else {
					return;
				}
			} else {
				return;
			}
		} else {
			return;
		}

		// Lay du lieu cho C1
		double xC1 = 0, yC1 = 0, zC1 = 0;
		xC1 = getAndParse(XC1TextFieldAddObject, XC1LabelAddObject);
		if (xC1 != -1) {
			yC1 = getAndParse(YC1TextFieldAddObject, YC1LabelAddObject);
			if (yC1 != -1) {
				zC1 = getAndParse(ZC1TextFieldAddObject, ZC1LabelAddObject);
				if (zC1 != -1) {
					C1 = new Point(xC1, yC1, zC1);
				} else {
					return;
				}
			} else {
				return;
			}
		} else {
			return;
		}

		// Lay du lieu cho height

		double height = getAndParse(heightTextFieldAddObject, heightLabelAddObject);
		if (height != -1) {
			if (height == 0) {
				heightLabelAddObject.setText("Height must be greater than 0!");
				heightLabelAddObject.setVisible(true);
				return;
			} else {
				heightLabelAddObject.setVisible(false);
			}
		} else {
			heightLabelAddObject.setText("Invalid Input!");
			return;
		}

		Dialog<String> dialog = new Dialog<>();

		if (Vector3D.isPerpendicular(A1, B1, C1)) {
			if (room.addObject(A1, B1, C1, height)) {
				dialog.setTitle("NOTIFICATION");
				dialog.setContentText("Add Object Success!");
				detailObjectEvent();
				flatViewDraw();
			} else {
				dialog.setTitle("WARNING");
				dialog.setHeaderText("Invalid Position!");
				dialog.setContentText("This Object's Position is Invalid!");
			}
		} else {
			dialog.setTitle("WARNING");
			dialog.setHeaderText("Invalid Shape!");
			dialog.setContentText("This Object isnot rectangular!");
		}

		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.show();

	}

	/**
	 * Event lam moi cac textField
	 */

	public void renewAddObjectEvent(ActionEvent e) {
		XA1TextFieldAddObject.setText(null);
		YA1TextFieldAddObject.setText(null);
		ZA1TextFieldAddObject.setText(null);
		// XA1LabelAddObject, YA1LabelAddObject, ZA1LabelAddObject;

		XB1TextFieldAddObject.setText(null);
		YB1TextFieldAddObject.setText(null);
		ZB1TextFieldAddObject.setText(null);
		// XB1LabelAddObject, YB1LabelAddObject, ZB1LabelAddObject;

		XC1TextFieldAddObject.setText(null);
		YC1TextFieldAddObject.setText(null);
		ZC1TextFieldAddObject.setText(null);
		// XC1LabelAddObject, YC1LabelAddObject, ZC1LabelAddObject;

		heightTextFieldAddObject.setText(null);
		// heightLabelAddObject;
	}

	/***** [ Add Camera Event ] *****/

	@FXML
	TextField xTextFieldAddCamera, yTextFieldAddCamera, zTextFieldAddCamera;
	@FXML
	Label xLabelAddCamera, yLabelAddCamera, zLabelAddCamera;

	@FXML
	TextField alphaTextFieldAddCamera, betaTextFieldAddCamera;
	@FXML
	Label alphaLabelAddCamera, betaLabelAddCamera;

	public void addCameraEvent(ActionEvent e) {
		double x = 0, y = 0, z = 0;
		Point p = new Point();

		x = getAndParse(xTextFieldAddCamera, xLabelAddCamera);
		if (x != -1) {
			y = getAndParse(yTextFieldAddCamera, yLabelAddCamera);
			if (y != -1) {
				z = getAndParse(zTextFieldAddCamera, zLabelAddCamera);
				if (z != -1) {
					p = new Point(x, y, z);
				} else {
					return;
				}
			} else {
				return;
			}
		} else {
			return;
		}

		double alpha, beta;
		alpha = getAndParse(alphaTextFieldAddCamera, alphaLabelAddCamera);
		if (alpha != -1) {
			if (alpha > 90) {
				alphaLabelAddCamera.setVisible(true);
				return;
			} else {
				alphaLabelAddCamera.setVisible(false);
			}
		} else {
			return;
		}

		beta = getAndParse(betaTextFieldAddCamera, betaLabelAddCamera);
		if (beta != -1) {
			if (beta > 90) {
				betaLabelAddCamera.setVisible(true);
				return;
			} else {
				betaLabelAddCamera.setVisible(false);
			}
		} else {
			return;
		}

		Dialog<String> dialog = new Dialog<>();

		if (room.addCameara(p, alpha, beta)) {
			dialog.setTitle("NOTIFICATION");
			dialog.setContentText("Add Camera Success!");
			detailCameraEvent();
			flatViewDraw();
		} else {
			dialog.setTitle("WARNING");
			dialog.setHeaderText("Invalid Position!");
			dialog.setContentText("This Camera's Position is Invalid!");
		}
		// System.out.println(p.getX() + "-" + p.getY() + "-" + p.getZ());
		// System.out.println(alpha + "-" + beta);

		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.show();
	}

	/**
	 * Event lam moi cac textField
	 */

	public void renewAddCameraEvent(ActionEvent e) {
		xTextFieldAddCamera.setText(null);
		yTextFieldAddCamera.setText(null);
		zTextFieldAddCamera.setText(null);
		// xLabelAddCamera, yLabelAddCamera, zLabelAddCamera;

		alphaTextFieldAddCamera.setText(null);
		betaTextFieldAddCamera.setText(null);
		// alphaLabelAddCamera, betaLabelAddCamera;
	}

	/***** [ View Event ] *****/

	@FXML
	AnchorPane flatView;
	

	public void flatViewDraw() {

		// Camera camera = new Camera(room, new Point(50, 50, 100), 60, 60);
		//
		// Point B = new Point(0, 40, 0);
		// Point A = new Point(100, 40, 0);
		// Point C = new Point(0, 60, 0);
		//
		// Rectangular rectangular = new Rectangular(A, B, C, 50);
		//
		// ArrayList<Point> projection = rectangular.projection(camera);
		//
		// Double []points = new Double[projection.size() * 2];
		//
		// for(int i = 0; i < points.length; i += 2) {
		// points[i] = (double) projection.get(i/2).getX();
		// points[i + 1] = (double) projection.get(i/2).getY();
		// }
//		 System.out.println(room.couldnotSeePoint().size());
//		 for(double d : room.couldnotSeePoint()) {
//		 System.out.println(d);
//		 }

//		Polygon polygon = new Polygon();
//		polygon.getPoints().addAll(room.couldnotSeePoint());
//		polygon.setFill(Color.YELLOW);
//
//		Group group = new Group(polygon);
//		flatView.getChildren().add(group);
//		flatView.
		
		Group group = new Group();
		
		double primaryX = room.getWidthRoom() / 1000;
		double primaryY = room.getLengthRoom() / 1000;
		
		ArrayList<Double> arr = room.couldnotSeePoint();
		for(int i = 0; i < arr.size(); i += 2) {
			Rectangle rectangle = new Rectangle(arr.get(i) *5, arr.get(i+1) *5, primaryX *5, primaryY *5);
			group.getChildren().add(rectangle);
		}
//		group.setVisible(true);
		flatView.getChildren().clear();
		flatView.getChildren().add(group);
	}

	/***** [ Check Point Event ] *****/

	@FXML
	TextField xTextFieldCheckPoint, yTextFieldCheckPoint, zTextFieldCheckPoint;
	@FXML
	Label xLabelCheckPoint, yLabelCheckPoint, zLabelCheckPoint;
	@FXML
	TextArea textAreaCheckPoint;

	public void checkPointEvent(ActionEvent e) {
		double x, y, z;

		x = getAndParse(xTextFieldCheckPoint, xLabelCheckPoint);
		if (x == -1)
			return;

		y = getAndParse(yTextFieldCheckPoint, yLabelCheckPoint);
		if (y == -1)
			return;

		z = getAndParse(zTextFieldCheckPoint, zLabelCheckPoint);
		if (y == -1)
			return;

		Point p = new Point(x, y, z);
		Dimension result = room.checkPoint(p);

		int n = (int) result.getWidth();

		if (n == -1) {
			textAreaCheckPoint.setText("This point is outside the room.");
			return;
		}

		if (n == 0) {
			textAreaCheckPoint
					.setText("This point is in the room. \nBut not in the view of the camera, not in the object ");
			return;
		}

		if (n == 1) {
			Rectangular r = room.getRectList().get(result.height);

			textAreaCheckPoint.setText("This point is in the object. \nThe object has the following parameters: \n");
			textAreaCheckPoint.appendText("A1: " + r.getA1().getProperties() + "\n");
			textAreaCheckPoint.appendText("B1: " + r.getB1().getProperties() + "\n");
			textAreaCheckPoint.appendText("C1: " + r.getC1().getProperties() + "\n");
			textAreaCheckPoint.appendText("Height: " + r.getH());

			return;
		}

		if (n == 2) {
			Camera camera = room.getCameraList().get(result.height);

			textAreaCheckPoint
					.setText("This point is in the view of the camera. \nThe camera has the following parameters: \n");
			textAreaCheckPoint.appendText("X: " + camera.getPosition().getProperties() + "\n");
			textAreaCheckPoint.appendText("Alpha: " + camera.getAngle1() + "\n");
			textAreaCheckPoint.appendText("Beta: " + camera.getAngle2() + "\n");

			return;
		}

		if (n == 3) {
			textAreaCheckPoint
					.setText("This point is in the view of the camera(s) \nbut has been blocked by the object.");
			return;
		}

	}

	/**
	 * Event lam moi cac textField
	 */

	public void renewCheckPointEvent(ActionEvent e) {
		xTextFieldCheckPoint.setText(null);
		yTextFieldCheckPoint.setText(null);
		zTextFieldCheckPoint.setText(null);

		textAreaCheckPoint.setText(null);
	}

	/***** [ show Detail Event ] *****/

	// Show Object Table

	@FXML
	TableView<ObjectProperties> objectTableView;
	// Column
	@FXML
	TableColumn<ObjectProperties, String> a1ColumnObject;
	@FXML
	TableColumn<ObjectProperties, String> b1ColumnObject;
	@FXML
	TableColumn<ObjectProperties, String> c1ColumnObject;
	@FXML TableColumn<ObjectProperties, Double> heightColumnObject;
	
	private ObservableList<ObjectProperties> objectList;

	public void detailObjectEvent() {
		objectList = FXCollections.observableArrayList();
		
		for(Rectangular object : room.getRectList()) {
			objectList.add(new ObjectProperties(object));
		}
		
		a1ColumnObject.setCellValueFactory(new PropertyValueFactory<ObjectProperties, String>("a1"));
		b1ColumnObject.setCellValueFactory(new PropertyValueFactory<ObjectProperties, String>("b1"));
		c1ColumnObject.setCellValueFactory(new PropertyValueFactory<ObjectProperties, String>("c1"));
		
		heightColumnObject.setCellValueFactory(new PropertyValueFactory<ObjectProperties, Double>("height"));
		
		objectTableView.setItems(objectList);
	}

	// Show camera Table

	@FXML
	TableView<CameraProperties> cameraTableView;
	// Column
	@FXML
	TableColumn<CameraProperties, String> positionColumnCamera;
	@FXML TableColumn<CameraProperties, Double> alphaAngleColumnCamera;
		@FXML TableColumn<CameraProperties, Double> betaAngleColumnCamera;
		
	private ObservableList<CameraProperties> cameraList;

	public void detailCameraEvent() {
		cameraList = FXCollections.observableArrayList();
		
		for(Camera cam : room.getCameraList()) {
			cameraList.add(new CameraProperties(cam));
		}
		
		positionColumnCamera.setCellValueFactory(new PropertyValueFactory<CameraProperties, String>("position"));		
		alphaAngleColumnCamera.setCellValueFactory(new PropertyValueFactory<CameraProperties, Double>("angle1"));
		betaAngleColumnCamera.setCellValueFactory(new PropertyValueFactory<CameraProperties, Double>("angle2"));
		
		cameraTableView.setItems(cameraList);
	}

	/****************************
	 * [ Initialize Method ]
	 ****************************************/

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {

		// Show Detail
		detailCameraEvent();
		detailObjectEvent();
	}

	/****************************
	 * [ Other Method ]
	 ****************************************/

	/**
	 * Lay text trong TextField, chuyen ve double, tra ve -1 neu that bai
	 * */
	
	public double getAndParse(TextField inputTextField, Label errorLabel) { 
		double inputdouble;
		try {
			inputdouble = Double.parseDouble(inputTextField.getText().trim());
			if(inputdouble < 0) {
				errorLabel.setVisible(true);
				return -1;
			} else {
				errorLabel.setVisible(false);
				return inputdouble;
			}
			
		} catch (Exception e) {
			errorLabel.setVisible(true);
			return -1;
		}
	}

}
