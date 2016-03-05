package com.TMAProject.moviebrowser.API.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tarekkma on 2/21/16.
 */
public class Cast {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("character_name")
    @Expose
    private String characterName;
    @SerializedName("url_small_image")
    @Expose
    private String urlSmallImage;
    @SerializedName("imdb_code")
    @Expose
    private String imdbCode;

    /**
     *
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The characterName
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     *
     * @param characterName
     *     The character_name
     */
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    /**
     *
     * @return
     *     The urlSmallImage
     */
    public String getUrlSmallImage() {
        return urlSmallImage;
    }

    /**
     *
     * @param urlSmallImage
     *     The url_small_image
     */
    public void setUrlSmallImage(String urlSmallImage) {
        this.urlSmallImage = urlSmallImage;
    }

    /**
     *
     * @return
     *     The imdbCode
     */
    public String getImdbCode() {
        return imdbCode;
    }

    /**
     *
     * @param imdbCode
     *     The imdb_code
     */
    public void setImdbCode(String imdbCode) {
        this.imdbCode = imdbCode;
    }
}
