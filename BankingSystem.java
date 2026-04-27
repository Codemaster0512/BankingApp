import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BankingSystem extends Application {

    double balance = 5000;

    @Override
    public void start(Stage stage) {

        // ==================================================
        // PAGE 1 : WELCOME PAGE
        // ==================================================

        Label title1 = new Label("🏦 Smart Banking App");
        title1.setFont(new Font("Verdana", 32));
        title1.setStyle("-fx-text-fill:white; -fx-font-weight:bold;");

        Label subtitle = new Label("Secure • Fast • Easy Banking");
        subtitle.setStyle("-fx-text-fill:#d1d5db; -fx-font-size:17px;");

        Label name = new Label("👤 Name: Your Name");
        Label roll = new Label("🎓 Roll No: 12345");
        Label course = new Label("📘 Course: BCA / MCA / CS");

        name.setStyle(infoStyle());
        roll.setStyle(infoStyle());
        course.setStyle(infoStyle());

        Button nextBtn = createButton("➡ Enter System", "#10b981");
        Button closeBtn = createButton("❌ Close App", "#ef4444");

        VBox card1 = createCard();
        card1.getChildren().addAll(title1, subtitle, name, roll, course, nextBtn, closeBtn);

        VBox root1 = new VBox(card1);
        root1.setAlignment(Pos.CENTER);
        root1.setStyle(bg1());

        Scene firstScene = new Scene(root1, 600, 700);

        // ==================================================
        // PAGE 2 : LOGIN PAGE
        // ==================================================

        Label loginTitle = new Label("🔐 User Login");
        loginTitle.setFont(new Font("Verdana", 30));
        loginTitle.setStyle("-fx-text-fill:white; -fx-font-weight:bold;");

        Label loginSub = new Label("Enter credentials to continue");
        loginSub.setStyle("-fx-text-fill:#d1d5db; -fx-font-size:16px;");

        TextField username = createField("Enter Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Enter Password");
        password.setMaxWidth(300);
        password.setStyle(fieldStyle());

        Button loginBtn = createButton("🔓 Login", "#10b981");
        Button backBtn = createButton("⬅ Back", "#f59e0b");

        Label loginMsg = new Label();

        VBox card2 = createCard();
        card2.getChildren().addAll(loginTitle, loginSub, username, password, loginBtn, backBtn, loginMsg);

        VBox root2 = new VBox(card2);
        root2.setAlignment(Pos.CENTER);
        root2.setStyle(bg2());

        Scene loginScene = new Scene(root2, 600, 700);

        // ==================================================
        // PAGE 3 : DASHBOARD
        // ==================================================

        Label title3 = new Label("💳 Banking Dashboard");
        title3.setFont(new Font("Verdana", 28));
        title3.setStyle("-fx-text-fill:white; -fx-font-weight:bold;");

        Label balanceLabel = new Label("Current Balance: ₹" + balance);
        balanceLabel.setFont(new Font("Arial", 24));
        balanceLabel.setStyle("-fx-text-fill:#34d399; -fx-font-weight:bold;");

        TextField amountField = createField("Enter Amount");

        Button depositBtn = createButton("💰 Deposit", "#10b981");
        Button withdrawBtn = createButton("💸 Withdraw", "#ef4444");
        Button exitBtn = createButton("🚪 Exit", "#f59e0b");

        Label msg = new Label();
        msg.setFont(new Font("Arial", 16));

        VBox card3 = createCard();
        card3.getChildren().addAll(title3, balanceLabel, amountField,
                depositBtn, withdrawBtn, exitBtn, msg);

        VBox root3 = new VBox(card3);
        root3.setAlignment(Pos.CENTER);
        root3.setStyle(bg3());

        Scene dashboardScene = new Scene(root3, 600, 720);

        // ==================================================
        // ACTIONS
        // ==================================================

        nextBtn.setOnAction(e -> stage.setScene(loginScene));

        closeBtn.setOnAction(e -> stage.close());

        loginBtn.setOnAction(e -> {

            if (username.getText().equals("admin") &&
                password.getText().equals("1234")) {

                username.clear();
                password.clear();
                loginMsg.setText("");
                stage.setScene(dashboardScene);

            } else {
                loginMsg.setText("❌ Invalid Username or Password");
                loginMsg.setStyle("-fx-text-fill:#f87171; -fx-font-size:15px;");
            }
        });

        backBtn.setOnAction(e -> {
            username.clear();
            password.clear();
            loginMsg.setText("");
            stage.setScene(firstScene);
        });

        depositBtn.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());

                if (amount <= 0) {
                    msg.setText("⚠ Enter valid amount");
                    msg.setStyle("-fx-text-fill:#fde047;");
                } else {
                    balance += amount;
                    balanceLabel.setText("Current Balance: ₹" + balance);
                    msg.setText("✅ Deposit Successful");
                    msg.setStyle("-fx-text-fill:#34d399;");
                    amountField.clear();
                }

            } catch (Exception ex) {
                msg.setText("❌ Invalid Input");
                msg.setStyle("-fx-text-fill:#f87171;");
            }
        });

        withdrawBtn.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());

                if (amount <= 0) {
                    msg.setText("⚠ Enter valid amount");
                    msg.setStyle("-fx-text-fill:#fde047;");
                } else if (amount > balance) {
                    msg.setText("❌ Insufficient Balance");
                    msg.setStyle("-fx-text-fill:#f87171;");
                } else {
                    balance -= amount;
                    balanceLabel.setText("Current Balance: ₹" + balance);
                    msg.setText("✅ Withdrawal Successful");
                    msg.setStyle("-fx-text-fill:#34d399;");
                    amountField.clear();
                }

            } catch (Exception ex) {
                msg.setText("❌ Invalid Input");
                msg.setStyle("-fx-text-fill:#f87171;");
            }
        });

        exitBtn.setOnAction(e -> {
            amountField.clear();
            msg.setText("");
            stage.setScene(firstScene);
        });

        // ==================================================
        // SHOW
        // ==================================================

        stage.setTitle("Smart Banking System");
        stage.setScene(firstScene);
        stage.show();
    }

    // ==================================================
    // BACKGROUNDS
    // ==================================================

    public String bg1() {
        return "-fx-background-color: linear-gradient(to bottom right, #0f172a, #1e3a8a, #0f172a);";
    }

    public String bg2() {
        return "-fx-background-color: linear-gradient(to bottom right, #111827, #065f46, #111827);";
    }

    public String bg3() {
        return "-fx-background-color: linear-gradient(to bottom right, #1f2937, #4c1d95, #111827);";
    }

    // ==================================================
    // CARD STYLE
    // ==================================================

    public VBox createCard() {
        VBox box = new VBox(18);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(35));
        box.setMaxWidth(420);
        box.setStyle(
            "-fx-background-color: rgba(255,255,255,0.10);" +
            "-fx-background-radius:25px;" +
            "-fx-border-color: rgba(255,255,255,0.18);" +
            "-fx-border-radius:25px;" +
            "-fx-border-width:1.5px;"
        );
        return box;
    }

    // ==================================================
    // TEXT STYLE
    // ==================================================

    public String infoStyle() {
        return "-fx-text-fill:#fbbf24; -fx-font-size:18px; -fx-font-weight:bold;";
    }

    // ==================================================
    // FIELD STYLE
    // ==================================================

    public String fieldStyle() {
        return "-fx-font-size:16px;" +
               "-fx-background-radius:16px;" +
               "-fx-padding:12px;" +
               "-fx-background-color:white;";
    }

    public TextField createField(String text) {
        TextField field = new TextField();
        field.setPromptText(text);
        field.setMaxWidth(300);
        field.setStyle(fieldStyle());
        return field;
    }

    // ==================================================
    // BUTTON STYLE
    // ==================================================

    public Button createButton(String text, String color) {
        Button btn = new Button(text);
        btn.setMinWidth(250);
        btn.setMinHeight(48);
        btn.setStyle(
            "-fx-background-color:" + color + ";" +
            "-fx-text-fill:white;" +
            "-fx-font-size:16px;" +
            "-fx-font-weight:bold;" +
            "-fx-background-radius:18px;" +
            "-fx-cursor:hand;"
        );
        return btn;
    }

    public static void main(String[] args) {
        launch();
    }
}