package org;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LocalizationService {

    private static LocalizationService instance;
    private Map<String, String> strings = new HashMap<>();
    private String currentLanguage;

    public static LocalizationService getInstance(){
        if (instance==null){
            instance = new LocalizationService();
            return instance;
        }
        else{
            return instance;
        }
    }

    public void loadStrings(String language) {

        this.currentLanguage = language;
        strings.clear();

        String sql = "SELECT `key`, `value` FROM localization_strings WHERE language = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, language);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String key = rs.getString("key");
                String value = rs.getString("value");
                strings.put(key, value);
            }

        } catch (SQLException e) {
            System.err.println("Error loading localization strings:");
            e.printStackTrace();
        }
    }

    public String getString(String key) {
        return strings.getOrDefault(key, key);
    }

    public Set<String> getAllKeys() {
        return strings.keySet();
    }
}