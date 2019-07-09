package com.yourcompany.bro.hi.comments;

 class ModelJson {

    private String postId,id,name,email, body;

    public String getPostId() {
        return postId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    ModelJson (String id, String body){
        this.id=id;
        this.body= body;

    }

}
