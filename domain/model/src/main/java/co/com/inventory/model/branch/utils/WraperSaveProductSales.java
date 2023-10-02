package co.com.inventory.model.branch.utils;

import java.util.List;

public class WraperSaveProductSales {
    private List<ProductSaleUtil> productSaleUtilList;
    private String uuid;
    private Long branchId;

    public WraperSaveProductSales(List<ProductSaleUtil> productSaleUtilList, String uuid, Long branchId) {
        this.productSaleUtilList = productSaleUtilList;
        this.uuid = uuid;
        this.branchId = branchId;
    }

    public List<ProductSaleUtil> getProductSaleUtilList() {
        return productSaleUtilList;
    }

    public void setProductSaleUtilList(List<ProductSaleUtil> productSaleUtilList) {
        this.productSaleUtilList = productSaleUtilList;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
}
