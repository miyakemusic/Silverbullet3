<template>
	<apexchart :options="options" :series="series"/>
</template>

<script setup lang="ts">
import axios from 'axios'
import { ref ,watch, onMounted} from 'vue'
import convertUrl from './MyUrl.ts'

const props = defineProps(['nodeid', "projectid", "url", "title", "unit"])

onMounted(() => {
	retrieve()
});

function retrieve() {
//	var url = '/api/project/v1/progress/cost/history'
	var url = props.url
	if (props.nodeid == undefined) {
		url = url + '?projectid=' + props.projectid
	}
	else {
		url = url + '?nodeid=' + props.nodeid
	}
	axios.get(convertUrl(url))
	.then(function (response) {
		series.value = 		[{
		    name: 'Plan',
		    type: 'column',
		    data: response.data.plan
		  }, {
		    name: 'Actual',
		    type: 'line',
		    data: response.data.actual
		  }]
		  
		options.value = 		  {
		        chart: {
		        height: 350,
		        type: 'line',
		      },
		      stroke: {
		        width: [0, 4]
		      },
		      title: {
		        text: props.title
		      },
		      dataLabels: {
		        enabled: true,
		        enabledOnSeries: [1]
		      },
		      labels: response.data.time,
		      yaxis: [{
		        title: {
		          text: props.unit,
		        },
		      
		      }]
		      }
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});	
}

var series = ref ([{
    name: 'Plan',
    type: 'column',
    data: []
  }, {
    name: 'Actual',
    type: 'line',
    data: []
  }])
  
var options = ref({
      chart: {
      height: 350,
      type: 'line',
    },
    stroke: {
      width: [0, 4]
    },
    title: {
      text: 'Traffic Sources'
    },
    dataLabels: {
      enabled: true,
      enabledOnSeries: [1]
    },
    labels: ['01 Jan 2001', '02 Jan 2001', '03 Jan 2001', '04 Jan 2001', '05 Jan 2001', '06 Jan 2001', '07 Jan 2001', '08 Jan 2001', '09 Jan 2001', '10 Jan 2001', '11 Jan 2001', '12 Jan 2001'],
    yaxis: [{
      title: {
        text: 'MYen',
      },
    
    }]
    });

</script>