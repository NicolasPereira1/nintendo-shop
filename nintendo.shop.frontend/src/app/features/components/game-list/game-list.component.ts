import { Component } from '@angular/core';
import {NgForOf} from "@angular/common";
import {GameItemComponent} from "./game-item/game-item.component";

@Component({
  selector: 'app-game-list',
  standalone: true,
  imports: [
    NgForOf,
    GameItemComponent
  ],
  templateUrl: './game-list.component.html',
})
export class GameListComponent {
  games = [
    {idx: 1, title: "Super Mario Sunshine", score: 4.7},
    {idx: 2, title: "Zelda Twilight Princess", score: 4.9},
    {idx: 3, title: "Super Mario Kart", score: 4.6},
  ];
  handleAction = (action: {idx: number, copy: boolean}) => {
    const game = this.games.find(game => game.idx === action.idx);
    action.copy && game ?
        this.games.push({...game, idx: this.games.length+1}) :
        this.games = this.games.filter(
            game => game.idx != action.idx
        );
  }
}
