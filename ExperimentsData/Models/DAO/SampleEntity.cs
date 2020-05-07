using System;
using System.Collections.Generic;
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
    }
}