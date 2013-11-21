function OnDivScroll(objetoListBox, quantidade)
{
    
    if (objetoListBox.options.length > quantidade)
    {
        objetoListBox.size=objetoListBox.options.length;
    }
    else
    {
        objetoListBox.size=quantidade;
    }
}

function OnSelectFocus(objetoListBox, objetoDiv, quantidade)
{
    if (objetoDiv.scrollLeft != 0)
    {
        objetoDiv.scrollLeft = 0;
    }

    if( objetoListBox.options.length > quantidade)
    {
        objetoListBox.focus();
        objetoListBox.size=quantidade;
    }
}