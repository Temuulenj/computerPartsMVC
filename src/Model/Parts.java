package Model;

import DAO.PartsDAO;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
public class Parts {
    int id;      //商品编号
    String name; //商品名称
    int unitPrise;//单价
    int amount;//库存数量
    String sort;//类别

    public Parts(){}

    public Parts( int id, String name, int unitPrise, int amount, String sort) {
        this.name = name;
        this.id = id;
        this.unitPrise = unitPrise;
        this.amount = amount;
        this.sort = sort;
    }

    public boolean Insert(){
        PartsDAO pd=new PartsDAO();
        if (pd.haveId(id)){
            Parts p=new PartsDAO().getParts(id);
            if(name.equals(p.name)) {
                this.amount = p.amount+amount;
                this.sort = p.sort;
            }
            else {
                System.out.println("商品名称与库存名称不符");
                System.out.println("库存名称："+p.name);
                return false;
            }
            return pd.Update(this);
        }
        return pd.Insert(this);
    }


    public boolean Delete(){
        return new PartsDAO().Delete(this.id);
    }

    public boolean Out(int num){
        PartsDAO pd=new PartsDAO();
        if(!pd.haveId(this.id)){
            System.out.println("数据不存在");
            return false;
        }
        Parts p=new PartsDAO().getParts(id);
        p.amount=pd.getParts(this.id).amount;
        if(p.amount<num){
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("库存不足！");
            a.show();
            return false;
        }
        p.amount-=num;
        return new PartsDAO().Update(p);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnitPrise() {
        return unitPrise;
    }

    public void setUnitPrise(int unitPrise) {
        this.unitPrise = unitPrise;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public ObservableList<Parts> getData(){
        try {
            return new PartsDAO().getObsData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
