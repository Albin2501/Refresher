import { Routes } from '@angular/router';
import { HomepageComponent } from './component/homepage/homepage.component';
import { GridComponent } from './component/grid/grid.component';
import { TextArtComponent } from './component/text-art/text-art.component';

export const routes: Routes = [
    { path: 'grid', component: GridComponent },
    { path: 'textArt', component: TextArtComponent },
    { path: '**', component: HomepageComponent }
];
