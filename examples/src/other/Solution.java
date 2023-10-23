package other;// you can also use imports, for example:

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


class Solution {

    private static List<Category> CATEGORIES;



    public static void main(String[] args) {
        Solution solution = new Solution();

        //1. load all categories to the  CATEGORIES list
        solution.loadCategories();
        for (Category category: CATEGORIES)
            System.out.println(category);
        //2. method getKeywords() gets the keywords for a category
        //3. method getLevel() gets the level for a category
        //4. method solution(categoryID) returns the level and keywords for a Category

        String[] solutionResults = solution.solution(10); // returns {"1", "Electronics", "Gadgets"}

        for(String val : solutionResults){
            System.out.println(val);
        }
    }

    Object[][] data = {
            {1, "Root", "Products", -1},
            {2, "Furniture", "Furniture", 1},
            {3, "Electronics", "Electronics, Gadgets", 1},
            {4, "Home Appliances", "Home, Appliances", 1},
            {5, "Major Appliances", "", 4},
            {6, "Minor Appliances", "", 4},
            {7, "Lawn & Garden", "Lawn, Garden", 4},
            {8, "Kitchen Appliances", "", 5},
            {9, "General Appliances", "", 5}
    };


    public String[] solution(int categoryID) {
        Category category = Solution.getCategory(categoryID);
        if (category == null)
            throw new NoSuchElementException("categoryID not found");
        List<String > result = new ArrayList<>();
        result.add(category.getLevel().toString());
        result.addAll(category.getKeywords());
        return result.toArray(String[]::new);

    }

    public void loadCategories() {
        CATEGORIES = new ArrayList<>();
        for(Object[] item : data){
            CATEGORIES.add(new Category(
                    (Integer) item[0],
                    (String) item[1],
                    parseKeywords((String) item[2]),
                    getParent((Integer) item[3])));
        }
    }

    private List<String> parseKeywords(String keywords) {
        if(keywords.length() == 0)
            return new ArrayList<>();
        return Arrays.asList(Arrays.stream(keywords.split(",")).map(String::trim).toArray(String[]::new));
    }

    public static Category getParent(Integer parentId) {
        if (parentId == -1)
            return null;
        List<Category>  categoryList =   CATEGORIES.stream()
                .filter(item -> parentId == item.id)
                .collect(Collectors.toList());
        if(categoryList.size() == 1)
            return categoryList.get(0);
        else
            return null;

    }

    public static Category getCategory(Integer categoryID) {
       List<Category>  categoryList =  CATEGORIES.stream()
                .filter(item -> categoryID == item.id)
                .collect(Collectors.toList());
       if(categoryList.size() == 1)
           return categoryList.get(0);
       else
           return null;
    }
}


class Category{

    final int id;
    final String name;
    final List<String> keywords;
    final Category parent;

    public Category(int id, String name, List<String> keywords, Category parentId) {
        this.id = id;
        this.name = name;
        this.keywords = keywords;
        this.parent = parentId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getParent() {
        return parent;
    }

    public List<String> getKeywords() {
        if(this.keywords.isEmpty())
            return parent.getKeywords();
        return this.keywords;
    }

    public Integer getLevel() {
        if(this.parent == null)
            return 0;
        return  parent.getLevel() + 1;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", keywords=" + keywords +
                ", parentId=" + (parent != null ? parent.getId() : null) +
                '}';
    }
}