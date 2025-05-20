import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GalaxyBotGUI extends JFrame implements ActionListener {

    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    public GalaxyBotGUI() {
        setTitle("GalaxyBot - Space Science Chatbot");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.addActionListener(this);

        sendButton = new JButton("Send");
        sendButton.addActionListener(this);

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.SOUTH);

        chatArea.append("👽 GalaxyBot: Hello! Ask me anything about space, planets, or the universe.\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GalaxyBotGUI bot = new GalaxyBotGUI();
            bot.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userInput = inputField.getText().toLowerCase();
        inputField.setText("");

        if (userInput.isEmpty()) return;

        chatArea.append("You: " + userInput + "\n");
        String response = getResponse(userInput);
        chatArea.append("GalaxyBot: " + response + "\n");
    }

    public String getResponse(String input) {
        if (input.contains("what is a planet")) {
            return "A planet is a celestial body orbiting a star, massive enough to be rounded by its own gravity, but not massive enough to cause fusion.";
        } else if (input.contains("mercury")) {
            return "Mercury is the closest planet to the Sun and the smallest in the Solar System. It has no atmosphere and extreme temperatures.";
        } else if (input.contains("venus")) {
            return "Venus is the second planet from the Sun and has a thick, toxic atmosphere that traps heat, making it the hottest planet.";
        } else if (input.contains("earth")) {
            return "Earth is the third planet from the Sun and the only known planet to support life. It has a protective atmosphere and liquid water.";
        } else if (input.contains("mars")) {
            return "Mars is the fourth planet and known as the Red Planet. It has the tallest volcano and the deepest canyon in the solar system.";
        } else if (input.contains("jupiter")) {
            return "Jupiter is the largest planet, a gas giant with a strong magnetic field and dozens of moons. It has a famous Great Red Spot storm.";
        } else if (input.contains("saturn")) {
            return "Saturn is known for its stunning ring system. It is a gas giant like Jupiter and has many moons, including Titan.";
        } else if (input.contains("uranus")) {
            return "Uranus is a gas giant with a bluish color due to methane and orbits the Sun on its side. It has faint rings.";
        } else if (input.contains("neptune")) {
            return "Neptune is the farthest known planet in our Solar System, dark, cold, and has supersonic winds in its atmosphere.";
        } else if (input.contains("pluto")) {
            return "Pluto is a dwarf planet in the Kuiper Belt. It was reclassified from a planet in 2006.";
        } else if (input.contains("how big is the universe") || input.contains("size of universe")) {
            return "The observable universe is about 93 billion light-years in diameter, but it may be infinitely large.";
        } else if (input.contains("what is the universe")) {
            return "The universe is all of space and time and their contents: planets, stars, galaxies, and all matter and energy.";
        } else if (input.contains("galaxy")) {
            return "A galaxy is a massive collection of stars, dust, and dark matter bound by gravity. Our galaxy is the Milky Way.";
        } else if (input.contains("big bang")) {
            return "The Big Bang theory states that the universe began about 13.8 billion years ago from a singularity and has been expanding since.";
        } else if (input.contains("life")) {
            return "So far, Earth is the only place where life is known to exist, but scientists are searching for life on Mars, Europa, and exoplanets.";
        } else {
            return "I'm still learning. Ask me about planets, stars, galaxies, or the universe!";
        }
    }
}
