import { Routes } from '@angular/router';
import { HomepageComponent } from './component/homepage/homepage.component';
import { GridComponent } from './component/grid/grid.component';

export const routes: Routes = [
    { path: 'grid', component: GridComponent },
    { path: '**', component: HomepageComponent }
];
