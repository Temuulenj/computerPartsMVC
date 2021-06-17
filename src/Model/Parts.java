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

    public String Insert(){
        PartsDAO pd=new PartsDAO();
        if (pd.haveId(id)){
            Parts p=new PartsDAO().getParts(id);
            if(!name.equals(p.name)||!sort.equals(p.sort)) {
                return "商品信息与库存信息不符，请核对后重新输入！\n库存信息：\n商品名称："+p.name+"  商品类型:"+p.sort+"\n";
            }
            else {
                this.amount = p.amount+amount;
                this.sort = p.sort;
            }
            if(pd.Update(this)){
                return "入库成功！\n";
            }
        }
        if(pd.Insert(this)){
            return "入库成功！\n";
        }
        else {
            return "入库失败！\n";
        }
    }

    public boolean update(){
        return new PartsDAO().Update(this);
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
