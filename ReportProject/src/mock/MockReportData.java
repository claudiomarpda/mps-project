package mock;

/**
 * Mock data of a list of objects with three fields.
 */
public interface MockReportData {
    String[][] MOST_VIEWED = {{"Viewed_Name_A", "Viewed_Brand_A, Viewed_Displacement_A"},
            {"Viewed_Name_B", "Viewed_Brand_B, Viewed_Displacement_B"},
            {"Viewed_Name_C", "Viewed_Brand_C, Viewed_Displacement_C"}};

    String[][] MOST_COMPARED = {{"Compared_Name_A", "Compared_Brand_A, Compared_Displacement_A"},
            {"Compared_Name_B", "Compared_Brand_B, Compared_Displacement_B"},
            {"Compared_Name_C", "Compared_Brand_C, Compared_Displacement_C"}};

    String[][] FAVORITE = {{"Favorite_Name_A", "Favorite_Brand_A, Favorite_Displacement_A"},
            {"Favorite_Name_B", "Favorite_Brand_B, Favorite_Displacement_B"},
            {"Favorite_Name_C", "Favorite_Brand_C, Favorite_Displacement_C"}};
}
