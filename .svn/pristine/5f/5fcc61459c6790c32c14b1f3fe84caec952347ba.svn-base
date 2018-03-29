function CLASS_LIANDONG_YAO(array) {
	// ���飬����������Դ
	this.array = array;
	this.indexName = '';
	this.obj = '';
	// ������SELECT
	// ��������ǰonchange��SELECT ID��Ҫ���õ�SELECT ID
	this.subSelectChange = function(selectName1, selectName2) {
		var obj1 = document.all[selectName1];
		var obj2 = document.all[selectName2];
		var objName = this.toString();
		var me = this;
		obj1.onchange = function() {
			me.optionChange(this.options[this.selectedIndex].value, obj2.id);
		}
	}
	// ���õ�һ��SELECT
	// ������indexNameָѡ����,selectNameָselect��ID
	this.firstSelectChange = function(indexName, selectName) {
		this.obj = document.all[selectName];
		this.indexName = indexName;
		this.optionChange(this.indexName, this.obj.id);
	}
	// indexNameָѡ����,selectNameָselect��ID
	this.optionChange = function(indexName, selectName) {
		var obj1 = document.all[selectName];
		var me = this;
		obj1.length = 0;
		obj1.options[0] = new Option("-请选择-", '');
		for (var i = 0; i < this.array.length; i++) {
			if (this.array[i][1] == indexName) {
				// alert(this.array[i][1]+" "+indexName);
				obj1.options[obj1.length] = new Option(this.array[i][2],
						this.array[i][0]);
			}
		}
	}

}