import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-game-item',
  standalone: true,
  imports: [],
  templateUrl: './game-item.component.html',
})
export class GameItemComponent {
  @Input("item")
  game!: {idx:number, title: string, score: number};
  @Output()
  itemEmitter = new EventEmitter<{ idx:number, copy:boolean }>();

  handleCopy = () => this.itemEmitter.emit({idx: this.game.idx, copy: true});
  handleDeletion = () => this.itemEmitter.emit({idx: this.game.idx, copy: false});
}
