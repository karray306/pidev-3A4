package com.esprit.veltun.model;

import com.esprit.veltun.model.base.BaseEntity;
public class Abonnement extends BaseEntity {
    private int Id_ab;
    private float Prix_ab;
  private String Type_ab;
   private int Id_offre;
 private int Duree;
    public int getDuree() {
        return Duree;
    }

    public void setDuree(int Duree) {
        this.Duree = Duree;
    }
 
    public String getType_ab() {
        return Type_ab;
    }

    public void setType_ab(String Type_ab) {
        this.Type_ab = Type_ab;
    }

   
  

    public int getId_offre() {
        return Id_offre;
    }

    public void setId_offre(int Id_offre) {
        this.Id_offre = Id_offre;
    }
   
  
  public Abonnement(int i) {
        this.Id_ab=Id_ab;
    }

    public Abonnement() {
   
    }
    public Abonnement(int Id_ab,String Type_ab,int Duree,float Prix_ab,int Id_offre) {
    this.Id_ab=Id_ab;
     this.Type_ab = Type_ab;
     this.Duree = Duree;
     this.Prix_ab=Prix_ab;
      this.Id_offre=Id_offre;
    }
    public int getId_ab() {
        return Id_ab;
    }

    public void setId_ab(int Id_ab) {
        this.Id_ab = Id_ab;
    }

    public float getPrix_ab() {
        return Prix_ab;
    }

    public void setPrix_ab(float Prix_ab) {
        this.Prix_ab = Prix_ab;
    }
    @Override
    public String toString() {
        return "abonnement{" + "Id_ab=" + Id_ab + ", Type_ab=" + Type_ab +", Duree=" + Duree +", Prix_ab=" + Prix_ab +'}';
    }
}