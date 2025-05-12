import { Injectable } from '@angular/core';
import { Preferences } from '@capacitor/preferences';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }
  
  async set(entries: any) {
    await Preferences.set({
      key: 'widget_entries',
      value: JSON.stringify(entries),
    });
  }

}
