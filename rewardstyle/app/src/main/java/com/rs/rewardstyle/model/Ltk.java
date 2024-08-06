package com.rs.rewardstyle.model;

import java.util.Date;
import java.util.List;

public class Ltk{

    private String hero_image;
    private int hero_image_width;
    private int hero_image_height;
    private String id;
    private String profile_id;
    private String video_media_id;
    private String status;
    private String caption;
    private String share_url;
    private Date date_created;
    private Date date_updated;
    private Object date_scheduled;
    private Date date_published;
    private List<String> product_ids;
    private String hash;


    public String getHero_image() {
        return hero_image;
    }

    public void setHero_image(String hero_image) {
        this.hero_image = hero_image;
    }

    public int getHero_image_width() {
        return hero_image_width;
    }

    public void setHero_image_width(int hero_image_width) {
        this.hero_image_width = hero_image_width;
    }

    public int getHero_image_height() {
        return hero_image_height;
    }

    public void setHero_image_height(int hero_image_height) {
        this.hero_image_height = hero_image_height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(String profile_id) {
        this.profile_id = profile_id;
    }

    public String getVideo_media_id() {
        return video_media_id;
    }

    public void setVideo_media_id(String video_media_id) {
        this.video_media_id = video_media_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public Date getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(Date date_updated) {
        this.date_updated = date_updated;
    }

    public Object getDate_scheduled() {
        return date_scheduled;
    }

    public void setDate_scheduled(Object date_scheduled) {
        this.date_scheduled = date_scheduled;
    }

    public Date getDate_published() {
        return date_published;
    }

    public void setDate_published(Date date_published) {
        this.date_published = date_published;
    }

    public List<String> getProduct_ids() {
        return product_ids;
    }

    public void setProduct_ids(List<String> product_ids) {
        this.product_ids = product_ids;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

}
