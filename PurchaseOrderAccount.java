

public class PurchaseOrderAccount extends ArrayList implements Cloneable{

    public PurchaseOrderAccount(){}

    public PurchaseOrderAccount(ArrayList arrayList){
        this.newList=arrayList.newList;
    }


    @Override
    protected PurchaseOrderAccount clone() throws CloneNotSupportedException {
        PurchaseOrderAccount account=(PurchaseOrderAccount) super.clone();
        //deep copy
        account.newList=new Object[account.newList.length];
        return account;
    }
}
