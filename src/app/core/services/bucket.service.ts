import { Injectable } from '@angular/core';
import { createClient } from '@supabase/supabase-js';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BucketService {

  private supabase = createClient(environment.supabaseConfig.url, environment.supabaseConfig.key);

  async uploadImage(blob: Blob, path: string): Promise<string> {
    const { data, error } = await this.supabase.storage.from('multimedia').upload(path, blob);
    if (error) throw error;

    const { publicUrl } = this.supabase.storage.from('multimedia').getPublicUrl(path).data;
    return publicUrl;
  }
}
