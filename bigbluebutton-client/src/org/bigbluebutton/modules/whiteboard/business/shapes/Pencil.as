/**
* BigBlueButton open source conferencing system - http://www.bigbluebutton.org/
* 
* Copyright (c) 2012 BigBlueButton Inc. and by respective authors (see below).
*
* This program is free software; you can redistribute it and/or modify it under the
* terms of the GNU Lesser General Public License as published by the Free Software
* Foundation; either version 3.0 of the License, or (at your option) any later
* version.
* 
* BigBlueButton is distributed in the hope that it will be useful, but WITHOUT ANY
* WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
* PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License along
* with BigBlueButton; if not, see <http://www.gnu.org/licenses/>.
*
*/
package org.bigbluebutton.modules.whiteboard.business.shapes
{
	/**
	 * The Pencil class. Extends a DrawObject 
	 * @author dzgonjan
	 * 
	 */	
	public class Pencil extends DrawObject
	{
		public function Pencil(id:String, type:String, status:String, userId:String) {
			super(id, type, status, userId);
		}
		
		override protected function makeGraphic(parentWidth:Number, parentHeight:Number, zoom:Number):void {
			this.graphics.clear();
			
			this.graphics.lineStyle(_ao.thickness * zoom, _ao.color);
			
			var graphicsCommands:Vector.<int> = new Vector.<int>();
			graphicsCommands.push(1);
			var coordinates:Vector.<Number> = new Vector.<Number>();
			coordinates.push(denormalize((_ao.points as Array)[0], parentWidth), denormalize((_ao.points as Array)[1], parentHeight));
			
			for (var i:int = 2; i < (_ao.points as Array).length; i += 2){
				graphicsCommands.push(2);
				coordinates.push(denormalize((_ao.points as Array)[i], parentWidth), denormalize((_ao.points as Array)[i+1], parentHeight));
			}
			
			this.graphics.drawPath(graphicsCommands, coordinates);
			this.alpha = 1;
		}
	}
}