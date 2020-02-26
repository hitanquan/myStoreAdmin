package com.admin.domain;

/**
 * 商品实体类
 *
 * @author 谭少
 */
public class Goods {

    private Integer id;
    private String name;
    private Double price;
    private String image;
    private String gdesc;
    private Integer is_hot;
    private Integer cid;

    public Goods() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGdesc() {
        return gdesc;
    }

    public void setGdesc(String gdesc) {
        this.gdesc = gdesc;
    }

    public Integer getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(Integer is_hot) {
        this.is_hot = is_hot;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Goods [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image + ", gdesc=" + gdesc
                + ", is_hot=" + is_hot + ", cid=" + cid + "]";
    }
}
