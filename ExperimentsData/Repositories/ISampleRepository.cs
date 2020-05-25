using System;
using System.Collections.Generic;
using ExperimentsData.Models.DAO;

namespace ExperimentsData.Repositories
{
    public interface ISampleRepository
    {
        List<SampleGroupedEntity> GetAllGrouped(Guid datasetGuid);
        void Save(SampleEntity map);
        object GetById(Guid sampleId);
    }
}