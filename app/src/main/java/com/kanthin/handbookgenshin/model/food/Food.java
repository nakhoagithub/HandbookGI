package com.kanthin.handbookgenshin.model.food;

import com.kanthin.handbookgenshin.model.character.talents.ItemMaterial;

import java.util.ArrayList;

public class Food {
    private Delicious delicious;
    private Normal normal;
    private Suspicious suspicious;
    private String basedish;
    private String description;
    private String character;
    private String effect;
    private String foodcategory;
    private String foodfilter;
    private String foodtype;
    private ImageFood images;
    private ArrayList<ItemMaterial> ingredients;
    private String name;
    private String rarity;
    private String key;

    public Food() {
    }

    public Delicious getDelicious() {
        return delicious;
    }

    public void setDelicious(Delicious delicious) {
        this.delicious = delicious;
    }

    public Normal getNormal() {
        return normal;
    }

    public void setNormal(Normal normal) {
        this.normal = normal;
    }

    public Suspicious getSuspicious() {
        return suspicious;
    }

    public void setSuspicious(Suspicious suspicious) {
        this.suspicious = suspicious;
    }

    public String getBasedish() {
        return basedish;
    }

    public void setBasedish(String basedish) {
        this.basedish = basedish;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getFoodcategory() {
        return foodcategory;
    }

    public void setFoodcategory(String foodcategory) {
        this.foodcategory = foodcategory;
    }

    public String getFoodfilter() {
        return foodfilter;
    }

    public void setFoodfilter(String foodfilter) {
        this.foodfilter = foodfilter;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype;
    }

    public ImageFood getImages() {
        return images;
    }

    public void setImages(ImageFood images) {
        this.images = images;
    }

    public ArrayList<ItemMaterial> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<ItemMaterial> ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
