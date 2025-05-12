import { Injectable } from '@angular/core';
import { addDoc, collection, collectionData, CollectionReference, deleteDoc, doc, docData, DocumentData, Firestore, getDocs } from '@angular/fire/firestore';
import { from, Observable } from 'rxjs';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root'
})
export class PictureService {

  private pictureCollection: CollectionReference<DocumentData>;

  constructor(private firestore: Firestore, private storageSrv: StorageService) {
    this.pictureCollection = collection(this.firestore, 'pictures');
  }

  async save(picture: any): Promise<void> {
    const data = {
      ...picture,
      createdAt: new Date().toISOString()
    };

    try {
      const docRef = await addDoc(this.pictureCollection, data);
      this.updateEntries()
      console.log('Documento agregado con ID:', docRef.id);
    } catch (error) {
      console.error('Error al agregar documento:', error);
      throw error;
    }
  }

  getAll(): Observable<any[]> {
    const multimediaRef = collection(this.firestore, 'pictures');
    return collectionData(multimediaRef, { idField: 'id' });
  }

  getById(id: string): Observable<any> {
    const docRef = doc(this.firestore, `pictures/${id}`);
    return docData(docRef, { idField: 'id' });
  }

  delete(id: string): Observable<void> {
    const docRef = doc(this.firestore, `pictures/${id}`);
    this.updateEntries()
    return from(deleteDoc(docRef));
  }

  private updateEntries(){
     this.getAll().subscribe((entries) => {
      this.storageSrv.set(entries);
     });
  }
}
