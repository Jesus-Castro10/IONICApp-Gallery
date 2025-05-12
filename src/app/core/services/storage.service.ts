import { Injectable } from '@angular/core';
import { Preferences } from '@capacitor/preferences';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }
  
  async saveEntry(entries: any) {
    console.log('MyWidget Saving entries', entries);
    await Preferences.set({
      key: 'widget_entries',
      value: JSON.stringify(entries),
    });
  }

}
