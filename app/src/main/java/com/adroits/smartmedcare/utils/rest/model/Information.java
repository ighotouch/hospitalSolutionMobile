package com.adroits.smartmedcare.utils.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Information {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("category")
@Expose
private String category;
@SerializedName("content")
@Expose
private String content;
@SerializedName("thumbnail")
@Expose
private String thumbnail;
@SerializedName("type")
@Expose
private String type;
@SerializedName("header")
@Expose
private String header;
@SerializedName("deleted_at")
@Expose
private Object deletedAt;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("updated_at")
@Expose
private String updatedAt;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getCategory() {
return category;
}

public void setCategory(String category) {
this.category = category;
}

public String getContent() {
return content;
}

public void setContent(String content) {
this.content = content;
}

public String getThumbnail() {
return thumbnail;
}

public void setThumbnail(String thumbnail) {
this.thumbnail = thumbnail;
}

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public String getHeader() {
return header;
}

public void setHeader(String header) {
this.header = header;
}

public Object getDeletedAt() {
return deletedAt;
}

public void setDeletedAt(Object deletedAt) {
this.deletedAt = deletedAt;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public String getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

}


