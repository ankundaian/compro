package ui;

import java.util.Collections;
import java.util.List;

import business.ControllerInterface;
import business.SystemController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Start extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	private static Stage primStage = null;
	public static Menu viewMenu, actionMenu, userMenu;
	public static MenuItem login, logout, addMember, editMember, addBook, addBookCopy, checkout, checkin, memRecord,
			overdue;

	public static Stage primStage() {
		return primStage;
	}

	public static class Colors {
		static Color green = Color.web("#034220");
		static Color red = Color.FIREBRICK;
	}

	private static Stage[] allWindows = { LoginWindow.INSTANCE, AllMembersWindow.INSTANCE, AllBooksWindow.INSTANCE,
			AddNewLibMemberWindow.INSTANCE, EditLibMember.INSTANCE, AddBookWindow.INSTANCE, AddAuthorWindow.INSTANCE,
			CheckoutBookWindow.INSTANCE, AddBookCopyWindow.INSTANCE, OverdueCopyWindow.INSTANCE,
			ViewBooksWindow.INSTANCE };

	public static void hideAllWindows() {
		primStage.hide();
		for (Stage st : allWindows) {
			st.close();
		}
	}

	@Override
	public void start(Stage primaryStage) {
		primStage = primaryStage;
		primaryStage.setTitle("Main Page");
		VBox topContainer = new VBox();
		topContainer.setId("top-container");
		MenuBar mainMenu = new MenuBar();
		VBox imageHolder = new VBox();
		Image image = new Image("ui/library.jpg", 400, 300, false, false);

		// simply displays in ImageView the image as is
		ImageView iv = new ImageView();
		iv.setImage(image);
		imageHolder.getChildren().add(iv);
		imageHolder.setAlignment(Pos.CENTER);
		HBox splashBox = new HBox();
		Label splashLabel = new Label("The Library System");
		splashLabel.setFont(Font.font("Trajan Pro", FontWeight.BOLD, 30));
		splashBox.getChildren().add(splashLabel);
		splashBox.setAlignment(Pos.CENTER);

		topContainer.getChildren().add(mainMenu);
		topContainer.getChildren().add(splashBox);
		topContainer.getChildren().add(imageHolder);

		viewMenu = new Menu("View");
		actionMenu = new Menu("Actions");
		userMenu = new Menu("User");
		login = new MenuItem("Login");
		logout = new MenuItem("Close");
		addMember = new MenuItem("Add Member");
		editMember = new MenuItem("Edit Member");
		addBook = new MenuItem("Add Book");
		addBookCopy = new MenuItem("Add Book Copy");
		checkout = new MenuItem("CheckOut");
		checkin = new MenuItem("CheckIn");
		memRecord = new MenuItem("Member Record");
		overdue = new MenuItem("Overdue Checkouts");

		login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				hideAllWindows();
				if (!LoginWindow.INSTANCE.isInitialized()) {
					LoginWindow.INSTANCE.init();
				}
				LoginWindow.INSTANCE.clear();
				LoginWindow.INSTANCE.show();
			}
		});

		/*
		 * MenuItem bookIds = new MenuItem("All Book Ids"); bookIds.setOnAction(new
		 * EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent e) { hideAllWindows(); if
		 * (!AllBooksWindow.INSTANCE.isInitialized()) { AllBooksWindow.INSTANCE.init();
		 * } ControllerInterface ci = new SystemController(); List<String> ids =
		 * ci.allBookIds(); Collections.sort(ids); StringBuilder sb = new
		 * StringBuilder(); for (String s : ids) { sb.append(s + "\n"); }
		 * AllBooksWindow.INSTANCE.setData(sb.toString());
		 * AllBooksWindow.INSTANCE.show(); } });
		 */

		MenuItem memberIds = new MenuItem("All Member Ids");
		memberIds.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				hideAllWindows();
				if (!AllMembersWindow.INSTANCE.isInitialized()) {
					AllMembersWindow.INSTANCE.init();
				}
				ControllerInterface ci = new SystemController();
				List<String> ids = ci.allMemberIds();
				Collections.sort(ids);
				System.out.println(ids);
				StringBuilder sb = new StringBuilder();
				for (String s : ids) {
					sb.append(s + "\n");
				}
				System.out.println(sb.toString());
				AllMembersWindow.INSTANCE.setData(sb.toString());
				AllMembersWindow.INSTANCE.show();
			}
		});
		MenuItem viewbooks = new MenuItem("All Books");
		viewbooks.setOnAction(e -> {
			Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.CLOSE);
			alert.setGraphic(null);
			alert.setHeaderText("");
			alert.setTitle("All Books In Library");
			ViewBooksWindow.INSTANCE.init();
			alert.getDialogPane().setContent(ViewBooksWindow.INSTANCE.getScene().getRoot());
			alert.showAndWait();
		});
		// actionListener for logout
		logout.setOnAction(e -> {
			// disable view and action menus
			/*
			 * viewMenu.setDisable(true); actionMenu.setDisable(true);
			 * userMenu.getItems().set(1, login); userMenu.getItems().remove(0);
			 */
			hideAllWindows();
		});
		// actionListener for addmember
		addMember.setOnAction(e -> {
			hideAllWindows();
			if (!AddNewLibMemberWindow.INSTANCE.isInitialized()) {
				AddNewLibMemberWindow.INSTANCE.init();
			}
			AddNewLibMemberWindow.INSTANCE.show();
		});

		editMember.setOnAction(e -> {
			hideAllWindows();
			if (!EditLibMember.INSTANCE.isInitialized()) {
				EditLibMember.INSTANCE.init();
			}
			EditLibMember.INSTANCE.show();
		});

		addBook.setOnAction(e -> {
			hideAllWindows();
			if (!AddBookWindow.INSTANCE.isInitialized()) {
				AddBookWindow.INSTANCE.init();
			}
			AddBookWindow.INSTANCE.show();
		});
		addBookCopy.setOnAction(e -> {
			hideAllWindows();
			if (!AddBookCopyWindow.INSTANCE.isInitialized()) {
				AddBookCopyWindow.INSTANCE.init();
			}
			AddBookCopyWindow.INSTANCE.show();
		});

		checkout.setOnAction(e -> {
			hideAllWindows();
			if (!CheckoutBookWindow.INSTANCE.isInitialized()) {
				CheckoutBookWindow.INSTANCE.init();
			}
			CheckoutBookWindow.INSTANCE.show();
		});

		memRecord.setOnAction(e -> {
			TextInputDialog inputDialog = new TextInputDialog();
			inputDialog.setHeaderText("");
			inputDialog.setTitle("Member ID");
			inputDialog.setContentText("Enter member ID ");
			inputDialog.showAndWait();
			String result = inputDialog.getResult();
			if (result != null) {
				String memberid = inputDialog.getEditor().getText().trim();
				SystemController.getMember(memberid).getCheckoutRecord()
						.forEach(rec -> System.out.println("Member(" + memberid + ")=> " + rec));
			}
		});

		overdue.setOnAction(e -> {
			hideAllWindows();
			if (!OverdueCopyWindow.INSTANCE.isInitialized()) {
				OverdueCopyWindow.INSTANCE.init();
			}
			OverdueCopyWindow.INSTANCE.show();
		});

		viewMenu.getItems().addAll(viewbooks, memberIds, memRecord, overdue);
		actionMenu.getItems().addAll(addMember, editMember, addBook, checkout, checkin);
		userMenu.getItems().add(login);
		// add to main menu
		mainMenu.getMenus().addAll(userMenu);
		mainMenu.getMenus().addAll(viewMenu);
		mainMenu.getMenus().addAll(actionMenu);
		//
		Scene scene = new Scene(topContainer, 420, 375);
		primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
		primaryStage.show();

		// disable views
		viewMenu.setDisable(true);
		actionMenu.setDisable(true);
	}

}