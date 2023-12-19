package com.example.easyshoplist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class ShoppingItems(
    val id:Int,
    var name: String,
    var quantity:Int,
    var isEditing: Boolean = false
)

@Composable
fun ShoppingApp(){
    var ShopItems by remember{ mutableStateOf(listOf<ShoppingItems>()) }
    var ShowDialog by remember { mutableStateOf(false) }
    var AddItem by remember{ mutableStateOf("") }
    var AddQuant by remember{ mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center

    ) {
       Button(onClick = {ShowDialog=true},
           modifier = Modifier
               .fillMaxWidth()
               .padding(32.dp),
           shape = RoundedCornerShape(8.dp)

           ){
           Text(text = "ADD ITEMS",
               color= Color.Black
           )

            }
        LazyColumn(
            modifier= Modifier
                .fillMaxSize()
                .padding(20.dp)
        ){
           items(ShopItems){
               item ->
               if(item.isEditing){
                   EditItems(item = item, onEditDone = {
                       editedName, editedQty->
                       ShopItems= ShopItems.map{it.copy(isEditing = false)}
                       val editedItem= ShopItems.find { it.id==item.id }
                       editedItem?.let {
                           it.name = editedName
                           it.quantity = editedQty
                       }
                   })
               }else{
                   listItems(item = item, onEdit = {
                       ShopItems= ShopItems.map{it.copy(isEditing =it.id==item.id)}
                   }, onDelete= {
                       ShopItems= ShopItems - item
                   })
               }
           }
        }

      }

    if (ShowDialog){
        AlertDialog(onDismissRequest = {ShowDialog=false },
            confirmButton = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                               Button(onClick = {
                                   if (AddItem.isNotBlank()){
                                       val newItem= ShoppingItems(
                                           id=ShopItems.size+1 ,
                                           name = AddItem,
                                           quantity = AddQuant.toInt()
                                       )
                                       ShopItems+=newItem
                                       ShowDialog=false
                                       AddItem=""
                                   }
                               }) {
                                   Text("Add",
                                       color=Color.Black)

                               }
                                Button(onClick = {ShowDialog=false }) {
                                        Text("Cancle",
                                            color=Color.Black)
                                }
                            }


            },
            title={ Text("Add Your List")},
            text = {
                Column{
                    OutlinedTextField(value = AddItem,
                        onValueChange = {AddItem= it},
                        singleLine = true,
                         modifier = Modifier.fillMaxWidth().padding(20.dp),
                        label= {Text("Mention Item")})

                    OutlinedTextField(value = AddQuant,
                        onValueChange = {AddQuant= it},
                        singleLine = true,
                         modifier = Modifier
                             .fillMaxWidth()
                             .padding(20.dp),
                        label = {Text("Mention Quantity")})
                }
            }
            )
        
    }
}

@Composable
fun listItems(
    item: ShoppingItems,
    onEdit:()-> Unit,
    onDelete:()-> Unit,
){
  Row(
      modifier= Modifier
          .padding(10.dp)
          .fillMaxWidth()
          .border(
              border = BorderStroke(3.dp, Color.Blue),
              shape = RoundedCornerShape(20)
          ),
      horizontalArrangement = Arrangement.SpaceBetween
  ){
      Text(text = item.name, modifier=Modifier.padding(10.dp))
      Text(text = "Quantity: ${item.quantity}", modifier=Modifier.padding(10.dp))
      Row(modifier=Modifier.padding(10.dp)) {
          IconButton(onClick =  onEdit ) {
              Icon(imageVector = Icons.Default.Edit, contentDescription = null)
          }

          IconButton(onClick =  onDelete ) {
              Icon(imageVector = Icons.Default.Delete, contentDescription = null)
          }
      }
  }
}


