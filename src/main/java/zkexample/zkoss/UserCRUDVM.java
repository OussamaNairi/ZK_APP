package zkexample.zkoss;
 
import java.util.HashMap;
 
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
 
import zkexample.domain.UserProfile;
import zkexample.service.CRUDService;
 
public class UserCRUDVM {
 
  @WireVariable
   private CRUDService CRUDService;
 
    private UserProfile selectedRecord;
 private String recordMode;
  private boolean makeAsReadOnly;
 
 public UserProfile getSelectedRecord() {
        return selectedRecord;
  }
 
   public void setSelectedRecord(UserProfile selectedRecord) {
     this.selectedRecord = selectedRecord;
   }
 
   public String getRecordMode() {
     return recordMode;
  }
 
   public void setRecordMode(String recordMode) {
      this.recordMode = recordMode;
   }
 
   public boolean isMakeAsReadOnly() {
     return makeAsReadOnly;
  }
 
   public void setMakeAsReadOnly(boolean makeAsReadOnly) {
     this.makeAsReadOnly = makeAsReadOnly;
   }
 
   @SuppressWarnings("unchecked")
  @AfterCompose
   public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
 
     UserProfile userProfile;
        Selectors.wireComponents(view, this, false);
 
        final HashMap<String, Object> map = (HashMap<String, Object>) Sessions
              .getCurrent().getAttribute("allmyvalues");
      this.recordMode = (String) map.get("recordMode");
       userProfile = (UserProfile) map.get("selectedRecord");
      CRUDService = (CRUDService) SpringUtil.getBean("CRUDService");
 
      if (recordMode.equals("NEW")) {
         this.selectedRecord = new UserProfile();
        }
 
       if (recordMode.equals("EDIT")) {
            this.selectedRecord = userProfile;
      }
 
       if (recordMode == "READ") {
         setMakeAsReadOnly(true);
            this.selectedRecord = userProfile;
      }
   }
 
   @Command
    public void saveThis(@BindingParam("action") Integer action) {
      CRUDService.Save(this.selectedRecord);
      if (action==0)
      {
           Executions.sendRedirect("userList.zul");
        }
       if (action==1)
      {
           this.selectedRecord = new UserProfile();
             BindUtils.postNotifyChange(null, null, UserCRUDVM.this,"selectedRecord");
 
      }
   }
    
    @Command
    public void cancel()
    {
       Executions.sendRedirect("userList.zul");
    }
 
}