using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using ExperimentsData.Models.Enums;

namespace ExperimentsData.Models.DAO
{
    public class SampleGroupedEntity : BaseEntity
    {
        public ExtractorType ExtractorType { get; set; }
        public long Attributes { get; set; }
        public string OriginalFileName { get; set; }
        public string label { get; set; }
        
        [NotMapped] 
        public Guid DatasetEntityId { get; set; }
     
        public SampleGroupedEntity(SampleEntity sampleEntity)
        {
            ExtractorType = sampleEntity.ExtractorType;
            OriginalFileName = sampleEntity.OriginalFileName;
            label = sampleEntity.label;
            DatasetEntityId = sampleEntity.DatasetEntityId;
            this.Attributes = sampleEntity.Attributes.Count();
            base.Id = sampleEntity.Id;

        }

        public SampleGroupedEntity(dynamic sampleGroupedEntity)
        {
            ExtractorType = sampleGroupedEntity.ExtractorType;
            OriginalFileName = sampleGroupedEntity.OriginalFileName;
            label = sampleGroupedEntity.label;
            DatasetEntityId = sampleGroupedEntity.DatasetEntityId;
            this.Attributes = sampleGroupedEntity.Attributes;
            base.Id = sampleGroupedEntity.Id;

        }
        public SampleGroupedEntity(dynamic sampleGroupedEntity, int count)
        {
            ExtractorType = sampleGroupedEntity.ExtractorType;
            OriginalFileName = sampleGroupedEntity.OriginalFileName;
            label = sampleGroupedEntity.label;
            DatasetEntityId = sampleGroupedEntity.DatasetEntityId;
            this.Attributes = count;
            base.Id = sampleGroupedEntity.Id;

        }
        
    }
}