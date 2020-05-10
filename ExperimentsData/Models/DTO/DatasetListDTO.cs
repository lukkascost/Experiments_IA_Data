using System;

namespace ExperimentsData.Models.DTO
{
    public class DatasetListDTO
    {
        public Guid Id { get; set; }
        public string description { get; set; }
        public string name { get; set; }
        public long Samples { get; set; }
    }
}