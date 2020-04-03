package zkexample.zkoss;
 
import java.util.HashMap;
import java.util.List;
 
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;
 
import zkexample.domain.UserProfile;
import zkexample.service.CRUDService;
 
public class UserListVM {
 
  @WireVariable
   private CRUDService CRUDService;
 
    private UserProfile selectedItem;
   private List<UserProfile> allReordsInDB = null;
 
   public UserProfile getSelectedItem() {
      return selectedItem;
    }
 
   public void setSelectedItem(UserProfile selectedItem) {
     this.selectedItem = selectedItem;
   }
 
   @AfterCompose
   public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
     Selectors.wireComponents(view, this, false);
        CRUDService = (CRUDService) SpringUtil.getBean("CRUDService");
      allReordsInDB = CRUDService.getAll(UserProfile.class);
  }
 
   public List<UserProfile> getDataSet() {
       return allReordsInDB;
   }
 
   @Command
    public void onAddNew() {
        final HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("selectedRecord", null);
        map.put("recordMode", "NEW");
       Sessions.getCurrent().setAttribute("allmyvalues", map);
     Executions.sendRedirect("UserCRUD.zul");
    }
 
   @Command
    public void onEdit(@BindingParam("userRecord") UserProfile userProfile) {
 
       final HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("selectedRecord", userProfile);
     map.put("recordMode", "EDIT");
      Sessions.getCurrent().setAttribute("allmyvalues", map);
     Executions.sendRedirect("UserCRUD.zul");
    }
 
   @Command
    public void openAsReadOnly(
         @BindingParam("userRecord") UserProfile userProfile) {
 
      final HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("selectedRecord", userProfile);
     map.put("recordMode", "READ");
      Sessions.getCurrent().setAttribute("allmyvalues", map);
     Executions.sendRedirect("UserCRUD.zul");
    }
 
   @SuppressWarnings({ "rawtypes", "unchecked" })
  @Command
    public void onDelete(@BindingParam("userRecord") UserProfile userProfile) {
     int OkCancel;
       this.selectedItem = userProfile;
        String str = "The Selected  \"" + userProfile.getUserLoginID()
              + "\" will be deleted.";
        OkCancel = Messagebox.show(str, "Confirm", Messagebox.OK
                | Messagebox.CANCEL, Messagebox.QUESTION);
      if (OkCancel == Messagebox.CANCEL) {
            return;
     }
 
       str = "The \""
              + userProfile.getUserLoginID()
              + "\" will be permanently deleted and the action cannot be undone.";
 
        Messagebox.show(str, "Confirm", Messagebox.OK | Messagebox.CANCEL,
              Messagebox.QUESTION, new EventListener() {
                  public void onEvent(Event event) throws Exception {
                     if (((Integer) event.getData()).intValue() == Messagebox.OK) {
 
                          CRUDService.delete(selectedItem);
                           allReordsInDB.remove(allReordsInDB
                                  .indexOf(selectedItem));
                            BindUtils.postNotifyChange(null, null,
                                  UserListVM.this, "dataSet");
 
                        }
                   }
               });
 }
 
}