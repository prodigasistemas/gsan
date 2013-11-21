/*
-----------------------------------------------------------------------------
	dTree 2.0  |  www.destroydrop.com/javascript/tree/
-----------------------------------------------------------------------------
	Copyright (c) 2002 Geir Landrö

	This script can be used freely as long as all copyright messages are
	intact.
-----------------------------------------------------------------------------
*/

// * Temporary solution for IE6 select's bug
function hideSelect()
{
	if (document.all)
	{
		document.all.ultimosacessos.style.visibility="hidden";
	}
}

function unhideSelect()
{
	if (document.all)
	{
		document.all.ultimosacessos.style.visibility="visible";
	}
}
// * End

// Node object
function Node(id, pid, name, url, title, target, isopen, img)
{
	this.id			= id;
	this.pid		= pid;
	this.name		= name;
	this.url		= url; //'/gsan/' + url;
	this.title	= title;
	this.target	= target;
	this.img		= img;

	this._io		= isopen || false;
	this._ls		= false;
	this._hc		= false;
	this._is		= false;
}


// Tree object
function dTree(objName)
{

// Variables
// ----------------------------------------------------------------------------

	this.arrNodes			= [];
	this.arrRecursed	= [];
	this.arrIcons			= [];
	this.rootNode			= -1;
	this.strOutput		= '';
	this.selectedNode	= null;

	this.instanceName = objName;
	this.imgFolder		= '/gsan/imagens/';
	this.target				= null;
	this.hasLines			= true;
	this.clickSelect	= true;
	this.folderLinks	= false;
	this.useCookies		= false;


// Functions
// ----------------------------------------------------------------------------


	// Adds a new node to the node array
	this.add = function(id, pid, name, url, title, target, isopen, img)
	{
		this.arrNodes[this.arrNodes.length] = new Node(id, pid, name, url, title, target, isopen, img);
	}

	// Outputs the tree to the page
	this.draw = function()
	{
		if (document.getElementById)
		{
			this.preloadIcons();
			if (this.useCookies) this.selectedNode = this.getSelected();
			this.addNode(this.rootNode);

			document.writeln(this.strOutput);
		}
		else
		{
			document.writeln('Browser not supported.');
		}
	}

	this.openAll = function()
	{
		this.oAll(true);
	}

	this.closeAll = function()
	{
		this.oAll(false);
	}


// Private
// ----------------------------------------------------------------------------

	// Prealoads images that are used in the tree
	this.preloadIcons = function()
	{
		if (this.hasLines)
		{
			this.arrIcons[0] = new Image();
			this.arrIcons[0].src = this.imgFolder + 'plus.gif';
			this.arrIcons[1] = new Image();
			this.arrIcons[1].src = this.imgFolder + 'plusbottom.gif';
			this.arrIcons[2] = new Image();
			this.arrIcons[2].src = this.imgFolder + 'minus.gif';
			this.arrIcons[3] = new Image();
			this.arrIcons[3].src = this.imgFolder + 'minusbottom.gif';
		}
		else
		{
			this.arrIcons[0] = new Image();
			this.arrIcons[0].src = this.imgFolder + 'nolines_plus.gif';
			this.arrIcons[1] = new Image();
			this.arrIcons[1].src = this.imgFolder + 'nolines_plus.gif';
			this.arrIcons[2] = new Image();
			this.arrIcons[2].src = this.imgFolder + 'nolines_minus.gif';
			this.arrIcons[3] = new Image();
			this.arrIcons[3].src = this.imgFolder + 'nolines_minus.gif';
		}
		this.arrIcons[4] = new Image();
		this.arrIcons[4].src = this.imgFolder + 'folder.gif';
		this.arrIcons[5] = new Image();
		this.arrIcons[5].src = this.imgFolder + 'folderopen.gif';
	}

	// Recursive function that creates the tree structure
	this.addNode = function(pNode)
	{
		for (var n=0; n<this.arrNodes.length; n++)
		{
			if (this.arrNodes[n].pid == pNode)
			{
				var cn = this.arrNodes[n];
				cn._hc = this.hasChildren(cn);
				cn._ls = (this.hasLines) ? this.lastSibling(cn) : false;
				if (cn._hc && !cn._io && this.useCookies) cn._io = this.isOpen(cn.id);

				if (this.clickSelect && cn.id == this.selectedNode)
				{
						cn._is = true;
						this.selectedNode = n;
				}

				if (!this.folderLinks && cn._hc) cn.url = null;


				// If the current node is not the root
				if (this.rootNode != cn.pid)
				{
					// Write out line & empty icons
					for (r=0; r<this.arrRecursed.length; r++)
						this.strOutput += '<img src="' + this.imgFolder + ( (this.arrRecursed[r] == 1 && this.hasLines) ? 'line' : 'empty' ) + '.gif" alt="" />';

					// Line & empty icons
					(cn._ls) ? this.arrRecursed.push(0) : this.arrRecursed.push(1);

					// Write out join icons
					if (cn._hc)
					{
						this.strOutput += '<a href="javascript: ' + this.instanceName + '.o(' + n + ');">'
							+ '<img id="j' + this.instanceName + n + '" src="' + this.imgFolder;
						if (!this.hasLines)
							this.strOutput += 'nolines_';

						this.strOutput += ( (cn._io) ? ((cn._ls && this.hasLines) ? 'minusbottom' : 'minus') : ((cn._ls && this.hasLines) ? 'plusbottom' : 'plus' ) )
							+ '.gif" alt="" /></a>';
					}
					else
						this.strOutput += '<img src="' + this.imgFolder + ( (this.hasLines) ? ((cn._ls) ? 'joinbottom' : 'join' ) : 'empty') + '.gif" alt="" />';
				}

				// Start the node link
				if (cn.url)
				{
					this.strOutput += '<a href="' + cn.url + '"';
					if (cn.title) this.strOutput += ' title="' + cn.title + '"';
					if (cn.target) this.strOutput += ' target="' + cn.target + '"';
					if (this.target && !cn.target) this.strOutput += ' target="' + this.target + '"';

					// If hightlight link is on
					if (this.clickSelect)
					{
						if (cn._hc)
						{
							if (this.folderLinks)
								this.strOutput += ' onclick="javascript: ' + this.instanceName + '.s(' + n + ');"';
						}
						else
						{
							this.strOutput += ' onclick="javascript: ' + this.instanceName + '.s(' + n + ');"';
						}
					}

					this.strOutput += '>';
				}
				if ((!this.folderLinks || !cn.url) && cn._hc && cn.pid != this.rootNode)
				{
					this.strOutput += '<a href="javascript: ' + this.instanceName + '.o(' + n + ');">';
				}

				// Write out folder & page icons
				this.strOutput += '<img id="i' + this.instanceName + n + '" src="' + this.imgFolder;
				this.strOutput += (cn.img) ? cn.img : ((this.rootNode == cn.pid) ? 'base' : (cn._hc) ? ((cn._io) ? 'folderopen' : 'folder') : 'page') + '.gif';
				this.strOutput += '" alt="" />';

				// Write out span
				this.strOutput += '<span id="s' + this.instanceName + n + '" class="' + ((this.clickSelect) ? ((cn._is ? 'nodeSel' : 'node')) : 'node') + '">';


				// Write out node name
				this.strOutput += cn.name;

					this.strOutput += '</span>';

				// Close the link
				if (cn.url || (!this.folderLinks && cn._hc)) this.strOutput += '</a>';

				this.strOutput += '<br />\n';

				// If node has children write out divs and go deeper
				if (cn._hc)
				{
					this.strOutput += '<div id="d' + this.instanceName + n + '" style="display:'
					+ ((this.rootNode == cn.pid || cn._io) ? 'block' : 'none')
					+ ';">\n';
					this.addNode(cn.id);
					this.strOutput += '</div>\n';
				}
				this.arrRecursed.pop();
			}
		}
	}

	// Checks if a node has any children
	this.hasChildren = function(node)
	{
		for (n=0; n<this.arrNodes.length; n++)
			if (this.arrNodes[n].pid == node.id) return true;
		return false;
	}

	// Checks if a node is the last sibling
	this.lastSibling = function(node)
	{
		var lastId;
		for (n=0; n< this.arrNodes.length; n++)
			if (this.arrNodes[n].pid == node.pid) lastId = this.arrNodes[n].id;
		if (lastId==node.id) return true;
		return false;
	}

	// Checks if a node id is in a cookie
	this.isOpen = function(id)
	{
		openNodes = this.getCookie('co' + this.instanceName).split('.');
		for (n=0;n<openNodes.length;n++)
			if (openNodes[n] == id) return true;
		return false;
	}

	// Checks if the node is selected
	this.isSelected = function(id)
	{
		selectedNode = this.getCookie('cs' + this.instanceName);
		if (selectedNode)
		{
			if (id==selectedNode)
			{
				this.selectedNode = id;
				return true
			}
		}
		return false;
	}

	// Returns the selected node
	this.getSelected = function()
	{
		selectedNode = this.getCookie('cs' + this.instanceName);
		if (selectedNode)	return selectedNode;
		return null;
	}

	// Highlights the selected node
	this.s = function(id)
	{
		cn = this.arrNodes[id];
		if (this.selectedNode != id)
		{
			if (this.selectedNode)
			{
				eOldSpan = document.getElementById("s" + this.instanceName + this.selectedNode);
				eOldSpan.className = "node";
			}

			eNewSpan = document.getElementById("s" + this.instanceName + id);
			eNewSpan.className = "nodeSel";

			this.selectedNode = id;
			if (this.useCookies) this.setCookie('cs' + this.instanceName, cn.id);
		}
	}

	// Toggle Open or close
	this.o = function(id)
	{
		cn = this.arrNodes[id];

		(cn._io) ? this.nodeClose(id,cn._ls) : this.nodeOpen(id,cn._ls);
		
		// * Temporary solution for IE6 select's bug
		if (id == 1) 
		{
			(cn._io) ? unhideSelect() : hideSelect();
		}
		// * End
		
		cn._io = !cn._io;

		if (this.useCookies) this.updateCookie();
	}

	// Open or close all nodes
	this.oAll = function(open)
	{
		for (n=0;n<this.arrNodes.length;n++)
		{
			if (this.arrNodes[n]._hc && this.arrNodes[n].pid != this.rootNode)
			{
				if (open)
				{
					this.nodeOpen(n, this.arrNodes[n]._ls);
					this.arrNodes[n]._io = true;
				}
				else
				{
					this.nodeClose(n, this.arrNodes[n]._ls);
					this.arrNodes[n]._io = false;
				}
			}
		}
		if (this.useCookies) this.updateCookie();
	}

	// Open a node
	this.nodeOpen = function(id, bottom)
	{
		eDiv	= document.getElementById('d' + this.instanceName + id);
		eJoin	= document.getElementById('j' + this.instanceName + id);
		eIcon	= document.getElementById('i' + this.instanceName + id);
		eJoin.src = (bottom) ?  this.arrIcons[3].src : this.arrIcons[2].src;
		if (!this.arrNodes[id].img) eIcon.src = this.arrIcons[5].src;
		eDiv.style.display = 'block';
		
		/*teste = document.getElementById('ultimosacessos');
		teste.style.visibility = "hidden";*/
	}

	// Close a node
	this.nodeClose = function(id, bottom)
	{
		eDiv	= document.getElementById('d' + this.instanceName + id);
		eJoin	= document.getElementById('j' + this.instanceName + id);
		eIcon	= document.getElementById('i' + this.instanceName + id);
		eJoin.src = (bottom) ? this.arrIcons[1].src : this.arrIcons[0].src;
		if (!this.arrNodes[id].img) eIcon.src = this.arrIcons[4].src;
		eDiv.style.display = 'none';


		/*if(id == '1'){
			teste = document.getElementById('ultimosacessos');
			teste.style.visibility = "visible";
		}*/
	}

	// Clears a cookie
	this.clearCookie = function()
	{
		var now = new Date();
		var yesterday = new Date(now.getTime() - 1000 * 60 * 60 * 24);
		this.setCookie('co'+this.instanceName, 'cookieValue', yesterday);
		this.setCookie('cs'+this.instanceName, 'cookieValue', yesterday);
	}

	// Sets value in a cookie
	this.setCookie = function(cookieName, cookieValue, expires, path, domain, secure) {
		document.cookie =
			escape(cookieName) + '=' + escape(cookieValue)
			+ (expires ? '; expires=' + expires.toGMTString() : '')
			+ (path ? '; path=' + path : '')
			+ (domain ? '; domain=' + domain : '')
			+ (secure ? '; secure' : '');
	}

	// Gets a value from a cookie
	this.getCookie = function(cookieName) {
		var cookieValue = '';
		var posName = document.cookie.indexOf(escape(cookieName) + '=');
		if (posName != -1)
		{
			var posValue = posName + (escape(cookieName) + '=').length;
			var endPos = document.cookie.indexOf(';', posValue);
			if (endPos != -1)
				cookieValue = unescape(document.cookie.substring(posValue, endPos));
			else
				cookieValue = unescape(document.cookie.substring(posValue));
		}
		return (cookieValue);
	}

	// Returns ids of open nodes as a string
	this.updateCookie = function()
	{
		sReturn = '';
		for (n=0;n<this.arrNodes.length;n++)
		{
			if (this.arrNodes[n]._io && this.arrNodes[n].pid != this.rootNode)
			{
				if (sReturn) sReturn += '.';
				sReturn += this.arrNodes[n].id;
			}
		}
		this.setCookie('co' + this.instanceName, sReturn);
	}

// tree object ends
}


// Functions used by the dTree object but are not really a part of it
// ------------------------------------------------------------------------------------------------

// Push and pop for arrays is not implemented in Internet Explorer
if (!Array.prototype.push) {
	Array.prototype.push = function array_push() {
		for(var i=0;i<arguments.length;i++)
			this[this.length]=arguments[i];
		return this.length;
	}
}
if (!Array.prototype.pop) {
	Array.prototype.pop = function array_pop() {
		lastElement = this[this.length-1];
		this.length = Math.max(this.length-1,0);
		return lastElement;
	}
}