//package de.hdm.itProjektSS17.client.gui;
//
//import com.google.gwt.user.client.ui.FlexTable;
//import com.google.gwt.user.client.ui.TextBox;
//
//import de.hdm.itProjektSS17.client.ClientsideSettings;
//import de.hdm.itProjektSS17.client.Showcase;
//
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.*;
//
//public class ProfilErstellenForm extends Showcase{
//	
//	private VerticalPanel vpanel = new VerticalPanel();
//	private FlexTable ftable = new FlexTable();
//	
//	
//	//Erstellen der Text- bzw. ListBoxen
//	private ListBox anredeBox = new ListBox();
//	private TextBox vnameBox = new TextBox();
//	private TextBox nnameBox = new TextBox();
//	private TextBox strasseBox = new TextBox();
//	private TextBox hausnrBox = new TextBox();
//	private TextBox plzBox = new TextBox();
//	private TextBox ortBox = new TextBox();
//	
//	
//	
//	
//	//Erstellen der Label
//	private Label anredeLabel = new Label("Anrede");
//	private Label vnameLabel = new Label("Vorname");
//	private Label nnameLabel = new Label("Nachname");
//	private Label strasseLabel = new Label("Straße");
//	private Label hausnrLabel = new Label("Hausnummer");
//	private Label plzLabel = new Label("Postleitzahl");
//	private Label ortLabel = new Label("Ort");
//
//	
//	//Erstellen der Buttons
//	private Button profilInsertButton = new Button("Speichern");
//	
//	
//		/**
//		 * Getter für die Attribute
//		 */
//		
//		public String getVorname(){
//			String vorname = vnameBox.getText();
//			
//			return vorname;
//		}
//		
//		public String getNachname(){
//			String nachname = nnameBox.getText();
//			
//			return nachname;
//		}
//		
//		public String getAnrede(){
//			String anrede = anredeBox.getSelectedValue();
//			
//			return anrede;
//		}
//		
//		public String getStrasse(){
//			String strasse = strasseBox.getText();
//			
//			return strasse;
//		}
//		
//		public String getHausnummer(){
//			String hausnummer = hausnrBox.getText();
//			
//			return hausnummer;
//		}
//		
//		public int getPLZ(){
//			int plz = plzBox.getValue();
//			
//			return plz;
//		}
//		
//		public String getOrt(){
//			String ort = ortBox.getSelectedText();
//			
//			return ort;
//		}
//		
//		
//		 
//		public class ProfilInsertClickHandler implements AsyncCallback{
//
//			@Override
//			public void onFailure(Throwable caught) {
//				vpanel.add(new Label("Fehler!!" + caught.toString()));
//				
//			}
//
//			@Override
//			public void onSuccess(Object result) {
//				// TODO Auto-generated method stub
//			
//				Showcase showcase = new ProfilErstellenForm();
//				RootPanel.get("Details").clear();
//				RootPanel.get("Details").add(showcase);
//			}
//			
//		}
//
//
//
//		@Override
//		protected String getHeadlineText() {
//			return "Profil Erstellen";
//		}
//		@Override
//		protected void run() {
//			try {
//				ClientsideSettings.getProjektmarktplatzVerwaltung().getPerson(callback)));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			/**
//			 * Anlegen der Widgets in einer FlexTable
//			 */
//			
//			ftable.setWidget(0, 1, anredeBox);
//			ftable.setWidget(0, 0, anredeLabel);
//
//			ftable.setWidget(1, 1, vnameBox);
//			ftable.setWidget(1, 0, vnameLabel);
//
//			ftable.setWidget(2, 1, nnameBox);
//			ftable.setWidget(2, 0, nnameLabel);
//
//			ftable.setWidget(3, 1, strasseBox);
//			ftable.setWidget(3, 0, strasseLabel);
//
//			ftable.setWidget(4, 1, hausnrBox);
//			ftable.setWidget(4, 0, hausnrLabel);
//			
//			ftable.setWidget(5, 1, plzBox);
//			ftable.setWidget(5, 0, plzLabel);
//
//			ftable.setWidget(6, 1, ortBox);
//			ftable.setWidget(6, 0, ortLabel);
//
//
//			/**
//			 * Befüllen der ListBox
//			 */
//			
//			anredeBox.addItem("Herr");
//			anredeBox.addItem("Frau");
//			
//			/**
//			 * Anfügen der FlexTable und des Buttons  an das Panel
//			 */
//			
//			this.add(ftable);
//			this.add(profilInsertButton);
//			profilInsertButton.addClickHandler(handler);
//			
//		}
//	
//}
//
