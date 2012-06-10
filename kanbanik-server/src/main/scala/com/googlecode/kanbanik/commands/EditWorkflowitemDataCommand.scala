package com.googlecode.kanbanik.commands;
import com.googlecode.kanbanik.dto.shell.SimpleParams
import com.googlecode.kanbanik.dto.shell.EditWorkflowParams
import com.googlecode.kanbanik.model.KanbanikEntity
import com.googlecode.kanbanik.dto.WorkflowitemDto
import com.googlecode.kanbanik.dto.shell.VoidParams
import com.googlecode.kanbanik.model.WorkflowitemScala
import org.bson.types.ObjectId

class EditWorkflowitemDataCommand extends ServerCommand[SimpleParams[WorkflowitemDto], VoidParams] with KanbanikEntity {
  def execute(params: SimpleParams[WorkflowitemDto]): VoidParams = {
    val id = params.getPayload().getId();
    val workflowitem = WorkflowitemScala.byId(new ObjectId(id))

    workflowitem.name = params.getPayload().getName()
    workflowitem.wipLimit = params.getPayload().getWipLimit()
    workflowitem.itemType = params.getPayload().getItemType().asStringValue()

    workflowitem.storeData
    new VoidParams
  }
}