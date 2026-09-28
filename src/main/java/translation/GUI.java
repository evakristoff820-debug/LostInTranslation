package translation;

import javax.swing.*;
import java.awt.*;

/**
 * GUI for the Country Name Translator.
 */
public class GUI {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // Create translator and language code converter
            Translator translator = new CanadaTranslator();
            LanguageCodeConverter converter = new LanguageCodeConverter();


            // -------------------------
            // Country panel
            // -------------------------

            JPanel countryPanel = new JPanel();

            JLabel countryLabel = new JLabel("Country:");

            JComboBox<String> countryBox = new JComboBox<>();

            // Add available country codes to the country dropdown
            for (String countryCode : translator.getCountryCodes()) {
                countryBox.addItem(countryCode);
            }

            countryPanel.add(countryLabel);
            countryPanel.add(countryBox);


            // -------------------------
            // Language panel
            // -------------------------

            JPanel languagePanel = new JPanel();

            JLabel languageLabel = new JLabel("Language:");

            JComboBox<String> languageBox = new JComboBox<>();

            // Add language names to the language dropdown
            for (String languageCode : translator.getLanguageCodes()) {

                String languageName =
                        converter.fromLanguageCode(languageCode);

                if (languageName != null) {
                    languageBox.addItem(languageName);
                }
            }

            languagePanel.add(languageLabel);
            languagePanel.add(languageBox);


            // -------------------------
            // Button and result panel
            // -------------------------

            JPanel buttonPanel = new JPanel();

            JButton submit = new JButton("Submit");

            JLabel resultLabelText = new JLabel("Translation:");

            JLabel resultLabel = new JLabel("");

            buttonPanel.add(submit);
            buttonPanel.add(resultLabelText);
            buttonPanel.add(resultLabel);


            // -------------------------
            // Submit button
            // -------------------------

            submit.addActionListener(e -> {

                // Get selected country code
                String countryCode =
                        (String) countryBox.getSelectedItem();

                // Get selected language name
                String language =
                        (String) languageBox.getSelectedItem();

                // Convert language name back to language code
                String languageCode =
                        converter.fromLanguage(language);

                // Translate the country
                String result =
                        translator.translate(countryCode, languageCode);

                // Handle missing translation
                if (result == null) {
                    result = "no translation found!";
                }

                resultLabel.setText(result);
            });


            // -------------------------
            // Main panel
            // -------------------------

            JPanel mainPanel = new JPanel();

            mainPanel.setLayout(
                    new BoxLayout(mainPanel, BoxLayout.Y_AXIS)
            );

            mainPanel.add(countryPanel);
            mainPanel.add(languagePanel);
            mainPanel.add(buttonPanel);


            // -------------------------
            // Frame
            // -------------------------

            JFrame frame =
                    new JFrame("Country Name Translator");

            frame.setContentPane(mainPanel);

            frame.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );

            frame.pack();

            frame.setLocationRelativeTo(null);

            frame.setVisible(true);
        });
    }
}