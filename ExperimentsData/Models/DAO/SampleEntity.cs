using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using ExperimentsData.Models.Enums;

namespace ExperimentsData.Models.DAO
{
    public class SampleEntity : BaseEntity
    {
        public ExtractorType ExtractorType { get; set; }
        public List<AttributeEntity> Attributes { get; set; }
        public string OriginalFileName { get; set; }
        public long order { get; set; }
        public string label { get; set; }
        
        [NotMapped] 
        public Guid DatasetEntityId { get; set; }

        public string toFile()
        {
            var atts =  this.Attributes.OrderBy(x=>x.order).ToList();
            string result = atts.Select(x => x.Value).Aggregate((x, old) => x + "," + old);
            result += ","+label;
            return result;
        }
    }
}